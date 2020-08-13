package com.azure.autorest.android.model;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.model.clientmodel.Proxy;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ServiceClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HostMapping {
    private final String host;
    private final List<String> hostParams;

    public String getEndpointExpression() {
        if (this.host == null
                || this.host.isEmpty()) {
            if (this.hostParams.isEmpty()) {
                return "";
            } else {
                return this.hostParams.get(0);
            }
        }
        if (this.hostParams.isEmpty()) {
            return this.host;
        } else {
            StringBuilder endpoint = new StringBuilder();
            endpoint.append(String.format("\"%s\"", this.host));
            for (String hostParam : this.hostParams) {
                endpoint.append(String.format(".replace(\"{%s}\", %s)", hostParam, hostParam));
            }
            return endpoint.append(";").toString();
        }
    }

    private HostMapping(String host, List<String> hostParams) {
        this.host = host;
        this.hostParams = hostParams;
    }

    public static HostMapping create(ServiceClient serviceClient) {
        Proxy proxy = null;
        // Find a proxy with host set and at least one method.
        //
        if (serviceClient.getProxy() != null &&
                serviceClient.getProxy().getBaseURL() != null &&
                !serviceClient.getProxy().getMethods().isEmpty()) {
            proxy = serviceClient.getProxy();
        } else {
            Optional<Proxy> proxyOpt = serviceClient.getMethodGroupClients()
                    .stream()
                    .filter(mg -> mg.getProxy() != null &&
                            mg.getProxy().getBaseURL() != null &&
                            !mg.getProxy().getMethods().isEmpty())
                    .map(mg -> mg.getProxy())
                    .findFirst();
            if (proxyOpt.isPresent()) {
                proxy = proxyOpt.get();
            }
        }
        if (proxy == null) {
            return new HostMapping(null, new ArrayList<>());
        }

        // @Host("{endpoint}/anomalydetector-ee/v1.0")
        final String host = proxy.getBaseURL();
        if (host == null || host.isEmpty()) {
            return new HostMapping(host, new ArrayList<>());
        }
        List<String> hostParams;
        ProxyMethod proxyMethod = proxy.getMethods().get(0);
        // find all @HostParam("endpoint")
        // In ServiceClient these are global parameters
        // http://azure.github.io/autorest/extensions/#x-ms-parameterized-host
        hostParams = proxyMethod
                .getParameters()
                .stream()
                .filter(p -> {
                    return p.getRequestParameterLocation() == RequestParameterLocation.Uri &&
                            !p.getIsConstant();
                })
                .map(p -> p.getName())
                .collect(Collectors.toList());

        String[] resolvedHost = new String[1];
        resolvedHost[0] = host;

        proxyMethod
                .getParameters()
                .stream()
                .filter(p -> {
                    return p.getRequestParameterLocation() == RequestParameterLocation.Uri &&
                            p.getIsConstant();
                }).forEach(parameter -> {
            if (resolvedHost[0].contains(String.format("{%s}", parameter.getName()))) {
                resolvedHost[0]
                        = resolvedHost[0].replace(String.format("{%s}",
                        parameter.getName()),
                        parameter.getDefaultValue());
            }
        });
        return new HostMapping(resolvedHost[0], hostParams);
    }
}