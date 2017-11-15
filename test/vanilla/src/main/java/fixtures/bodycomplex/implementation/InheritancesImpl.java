/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package fixtures.bodycomplex.implementation;

import com.google.common.reflect.TypeToken;
import com.microsoft.rest.v2.RestProxy;
import com.microsoft.rest.v2.RestResponse;
import com.microsoft.rest.v2.ServiceCallback;
import com.microsoft.rest.v2.ServiceFuture;
import com.microsoft.rest.v2.Validator;
import com.microsoft.rest.v2.annotations.BodyParam;
import com.microsoft.rest.v2.annotations.ExpectedResponses;
import com.microsoft.rest.v2.annotations.GET;
import com.microsoft.rest.v2.annotations.Headers;
import com.microsoft.rest.v2.annotations.Host;
import com.microsoft.rest.v2.annotations.PUT;
import com.microsoft.rest.v2.annotations.UnexpectedResponseExceptionType;
import com.microsoft.rest.v2.http.HttpClient;
import fixtures.bodycomplex.Inheritances;
import fixtures.bodycomplex.models.ErrorException;
import fixtures.bodycomplex.models.Siamese;
import java.io.IOException;
import rx.Observable;
import rx.Single;
import rx.functions.Func1;

/**
 * An instance of this class provides access to all the operations defined in
 * Inheritances.
 */
public class InheritancesImpl implements Inheritances {
    /** The RestProxy service to perform REST calls. */
    private InheritancesService service;
    /** The service client containing this operation class. */
    private AutoRestComplexTestServiceImpl client;

    /**
     * Initializes an instance of Inheritances.
     *
     * @param client the instance of the service client containing this operation class.
     */
    public InheritancesImpl(AutoRestComplexTestServiceImpl client) {
        this.service = RestProxy.create(InheritancesService.class, client.restClient().baseURL(), client.httpClient(), client.serializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for Inheritances to be used by
     * RestProxy to perform REST calls.
     */
    @Host("http://localhost")
    interface InheritancesService {
        @Headers({ "x-ms-logging-context: fixtures.bodycomplex.Inheritances getValid" })
        @GET("complex/inheritance/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<RestResponse<Void, Siamese>> getValid();

        @Headers({ "x-ms-logging-context: fixtures.bodycomplex.Inheritances putValid" })
        @PUT("complex/inheritance/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<RestResponse<Void, Void>> putValid(@BodyParam("application/json; charset=utf-8") Siamese complexBody);

    }

    /**
     * Get complex types that extend others.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Siamese object if successful.
     */
    public Siamese getValid() {
        return getValidAsync().toBlocking().value();
    }

    /**
     * Get complex types that extend others.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Siamese> getValidAsync(ServiceCallback<Siamese> serviceCallback) {
        return ServiceFuture.fromBody(getValidAsync(), serviceCallback);
    }

    /**
     * Get complex types that extend others.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return a {@link Single} emitting the RestResponse<Void, Siamese> object
     */
    public Single<RestResponse<Void, Siamese>> getValidWithRestResponseAsync() {
        return service.getValid();
    }

    /**
     * Get complex types that extend others.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return a {@link Single} emitting the RestResponse<Void, Siamese> object
     */
    public Single<Siamese> getValidAsync() {
        return getValidWithRestResponseAsync()
            .map(new Func1<RestResponse<Void, Siamese>, Siamese>() { public Siamese call(RestResponse<Void, Siamese> restResponse) { return restResponse.body(); } });
        }


    /**
     * Put complex types that extend others.
     *
     * @param complexBody Please put a siamese with id=2, name="Siameee", color=green, breed=persion, which hates 2 dogs, the 1st one named "Potato" with id=1 and food="tomato", and the 2nd one named "Tomato" with id=-1 and food="french fries".
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the void object if successful.
     */
    public void putValid(Siamese complexBody) {
        putValidAsync(complexBody).toBlocking().value();
    }

    /**
     * Put complex types that extend others.
     *
     * @param complexBody Please put a siamese with id=2, name="Siameee", color=green, breed=persion, which hates 2 dogs, the 1st one named "Potato" with id=1 and food="tomato", and the 2nd one named "Tomato" with id=-1 and food="french fries".
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> putValidAsync(Siamese complexBody, ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(putValidAsync(complexBody), serviceCallback);
    }

    /**
     * Put complex types that extend others.
     *
     * @param complexBody Please put a siamese with id=2, name="Siameee", color=green, breed=persion, which hates 2 dogs, the 1st one named "Potato" with id=1 and food="tomato", and the 2nd one named "Tomato" with id=-1 and food="french fries".
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return a {@link Single} emitting the RestResponse<Void, Void> object
     */
    public Single<RestResponse<Void, Void>> putValidWithRestResponseAsync(Siamese complexBody) {
        if (complexBody == null) {
            throw new IllegalArgumentException("Parameter complexBody is required and cannot be null.");
        }
        Validator.validate(complexBody);
        return service.putValid(complexBody);
    }

    /**
     * Put complex types that extend others.
     *
     * @param complexBody Please put a siamese with id=2, name="Siameee", color=green, breed=persion, which hates 2 dogs, the 1st one named "Potato" with id=1 and food="tomato", and the 2nd one named "Tomato" with id=-1 and food="french fries".
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return a {@link Single} emitting the RestResponse<Void, Void> object
     */
    public Single<Void> putValidAsync(Siamese complexBody) {
        return putValidWithRestResponseAsync(complexBody)
            .map(new Func1<RestResponse<Void, Void>, Void>() { public Void call(RestResponse<Void, Void> restResponse) { return restResponse.body(); } });
        }


}
