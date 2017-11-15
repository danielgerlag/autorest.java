/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package fixtures.custombaseuri.implementation;

import com.google.common.reflect.TypeToken;
import com.microsoft.rest.v2.RestProxy;
import com.microsoft.rest.v2.RestResponse;
import com.microsoft.rest.v2.ServiceCallback;
import com.microsoft.rest.v2.ServiceFuture;
import com.microsoft.rest.v2.annotations.ExpectedResponses;
import com.microsoft.rest.v2.annotations.GET;
import com.microsoft.rest.v2.annotations.Headers;
import com.microsoft.rest.v2.annotations.Host;
import com.microsoft.rest.v2.annotations.HostParam;
import com.microsoft.rest.v2.annotations.PathParam;
import com.microsoft.rest.v2.annotations.UnexpectedResponseExceptionType;
import com.microsoft.rest.v2.http.HttpClient;
import fixtures.custombaseuri.Paths;
import fixtures.custombaseuri.models.ErrorException;
import java.io.IOException;
import rx.Observable;
import rx.Single;
import rx.functions.Func1;

/**
 * An instance of this class provides access to all the operations defined in
 * Paths.
 */
public class PathsImpl implements Paths {
    /** The RestProxy service to perform REST calls. */
    private PathsService service;
    /** The service client containing this operation class. */
    private AutoRestParameterizedHostTestClientImpl client;

    /**
     * Initializes an instance of Paths.
     *
     * @param client the instance of the service client containing this operation class.
     */
    public PathsImpl(AutoRestParameterizedHostTestClientImpl client) {
        this.service = RestProxy.create(PathsService.class, client.restClient().baseURL(), client.httpClient(), client.serializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for Paths to be used by
     * RestProxy to perform REST calls.
     */
    @Host("http://{accountName}{host}")
    interface PathsService {
        @Headers({ "x-ms-logging-context: fixtures.custombaseuri.Paths getEmpty" })
        @GET("customuri")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<RestResponse<Void, Void>> getEmpty(@HostParam("accountName") String accountName, @HostParam("host") String host);

    }

    /**
     * Get a 200 to test a valid base uri.
     *
     * @param accountName Account Name
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the void object if successful.
     */
    public void getEmpty(String accountName) {
        getEmptyAsync(accountName).toBlocking().value();
    }

    /**
     * Get a 200 to test a valid base uri.
     *
     * @param accountName Account Name
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> getEmptyAsync(String accountName, ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(getEmptyAsync(accountName), serviceCallback);
    }

    /**
     * Get a 200 to test a valid base uri.
     *
     * @param accountName Account Name
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return a {@link Single} emitting the RestResponse<Void, Void> object
     */
    public Single<RestResponse<Void, Void>> getEmptyWithRestResponseAsync(String accountName) {
        if (accountName == null) {
            throw new IllegalArgumentException("Parameter accountName is required and cannot be null.");
        }
        if (this.client.host() == null) {
            throw new IllegalArgumentException("Parameter this.client.host() is required and cannot be null.");
        }
        return service.getEmpty(accountName, this.client.host());
    }

    /**
     * Get a 200 to test a valid base uri.
     *
     * @param accountName Account Name
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return a {@link Single} emitting the RestResponse<Void, Void> object
     */
    public Single<Void> getEmptyAsync(String accountName) {
        return getEmptyWithRestResponseAsync(accountName)
            .map(new Func1<RestResponse<Void, Void>, Void>() { public Void call(RestResponse<Void, Void> restResponse) { return restResponse.body(); } });
        }


}
