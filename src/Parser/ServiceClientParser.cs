// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Net;
using System.Text;
using AutoRest.Core;
using AutoRest.Core.Utilities;
using AutoRest.Extensions;
using AutoRest.Core.Model;
using Newtonsoft.Json;
using AutoRest.Core.Utilities.Collections;
using Newtonsoft.Json.Linq;
using AutoRest.Java.Model;

namespace AutoRest.Java
{
    public class ServiceClientParser : IParser<CodeModelJv, ServiceClient>
    {
        private JavaSettings settings;
        private ParserFactory factory;

        public ServiceClientParser(ParserFactory factory)
        {
            this.settings = factory.Settings;
            this.factory = factory;
        }

        public ServiceClient Parse(CodeModelJv codeModel)
        {
            string serviceClientInterfaceName = settings.ClientTypePrefix??"" + codeModel.Name;

            string serviceClientClassName = serviceClientInterfaceName;
            if (settings.GenerateClientInterfaces)
            {
                serviceClientClassName += "Impl";
            }

            Proxy serviceClientRestAPI = null;
            IEnumerable<ClientMethod> serviceClientMethods = Enumerable.Empty<ClientMethod>();
            IEnumerable<MethodJv> codeModelRestAPIMethods = codeModel.Methods
                .Cast<MethodJv>()
                .Where(m => m.Group.IsNullOrEmpty());
            if (codeModelRestAPIMethods.Any())
            {
                string restAPIName = serviceClientInterfaceName + "Service";
                string restAPIBaseURL = codeModel.BaseUrl;
                List<ProxyMethod> restAPIMethods = new List<ProxyMethod>();
                foreach (MethodJv codeModelRestAPIMethod in codeModelRestAPIMethods)
                {
                    ProxyMethod restAPIMethod = factory.GetParser<MethodJv, ProxyMethod>().Parse(codeModelRestAPIMethod);
                    restAPIMethods.Add(restAPIMethod);
                }
                serviceClientRestAPI = new Proxy(restAPIName, restAPIBaseURL, restAPIMethods);
                serviceClientMethods = codeModelRestAPIMethods.SelectMany(m => factory.GetParser<MethodJv, IEnumerable<ClientMethod>>().Parse(m));
            }

            List<MethodGroupClient> serviceClientMethodGroupClients = new List<MethodGroupClient>();
            IEnumerable<MethodGroup> codeModelMethodGroups = codeModel.Operations.Where((MethodGroup methodGroup) => !string.IsNullOrEmpty(methodGroup?.Name?.ToString()));
            foreach (MethodGroupJv codeModelMethodGroup in codeModelMethodGroups)
            {
                serviceClientMethodGroupClients.Add(factory.GetParser<MethodGroupJv, MethodGroupClient>().Parse(codeModelMethodGroup));
            }

            bool usesCredentials = false;

            List<ServiceClientProperty> serviceClientProperties = new List<ServiceClientProperty>();
            foreach (Property codeModelServiceClientProperty in codeModel.Properties)
            {
                string serviceClientPropertyDescription = codeModelServiceClientProperty.Documentation.ToString();

                string serviceClientPropertyName = CodeNamer.Instance.RemoveInvalidCharacters(codeModelServiceClientProperty.Name.ToCamelCase());

                IType serviceClientPropertyClientType = factory.GetParser<IModelTypeJv, IType>().Parse((IModelTypeJv) ((PropertyJv)codeModelServiceClientProperty).ModelType);
                if (codeModelServiceClientProperty.IsNullable() && serviceClientPropertyClientType != null)
                {
                    serviceClientPropertyClientType = serviceClientPropertyClientType.AsNullable();
                }

                bool serviceClientPropertyIsReadOnly = codeModelServiceClientProperty.IsReadOnly;

                string serviceClientPropertyDefaultValueExpression = serviceClientPropertyClientType.DefaultValueExpression(codeModelServiceClientProperty.DefaultValue);

                if (serviceClientPropertyClientType == ClassType.ServiceClientCredentials)
                {
                    usesCredentials = true;
                }
                else
                {
                    serviceClientProperties.Add(new ServiceClientProperty(serviceClientPropertyDescription, serviceClientPropertyClientType, serviceClientPropertyName, serviceClientPropertyIsReadOnly, serviceClientPropertyDefaultValueExpression));
                }
            }

            List<Constructor> serviceClientConstructors = new List<Constructor>();
            string constructorDescription = $"Initializes an instance of {serviceClientInterfaceName} client.";
            if (settings.IsAzureOrFluent)
            {
                if (usesCredentials)
                {
                    serviceClientConstructors.Add(new Constructor(codeModel.ServiceClientCredentialsParameter.Value));
                    serviceClientConstructors.Add(new Constructor(codeModel.ServiceClientCredentialsParameter.Value, codeModel.AzureEnvironmentParameter.Value));
                }
                else
                {
                    serviceClientConstructors.Add(new Constructor());
                    serviceClientConstructors.Add(new Constructor(codeModel.AzureEnvironmentParameter.Value));
                }

                serviceClientConstructors.Add(new Constructor(codeModel.HttpPipelineParameter.Value));
                serviceClientConstructors.Add(new Constructor(codeModel.HttpPipelineParameter.Value, codeModel.AzureEnvironmentParameter.Value));
            }
            else
            {
                serviceClientConstructors.Add(new Constructor());
                serviceClientConstructors.Add(new Constructor(codeModel.HttpPipelineParameter.Value));
            }

            return new ServiceClient(serviceClientClassName, serviceClientInterfaceName, serviceClientRestAPI, serviceClientMethodGroupClients, serviceClientProperties, serviceClientConstructors, serviceClientMethods, codeModel.AzureEnvironmentParameter, codeModel.ServiceClientCredentialsParameter, codeModel.HttpPipelineParameter);
        }
    }
}