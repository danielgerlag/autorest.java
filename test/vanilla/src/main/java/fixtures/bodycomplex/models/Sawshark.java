// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for
// license information.
//
// Code generated by Microsoft (R) AutoRest Code Generator.
// Changes may cause incorrect behavior and will be lost if the code is
// regenerated.

package fixtures.bodycomplex.models;

import com.azure.core.implementation.annotation.Fluent;
import com.azure.core.implementation.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The Sawshark model.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "fishtype")
@JsonTypeName("sawshark")
@Fluent
public final class Sawshark extends Shark {
    /*
     * The picture property.
     */
    @JsonProperty(value = "picture")
    private byte[] picture;

    /**
     * Get the picture property: The picture property.
     *
     * @return the picture value.
     */
    public byte[] picture() {
        return CoreUtils.clone(this.picture);
    }

    /**
     * Set the picture property: The picture property.
     *
     * @param picture the picture value to set.
     * @return the Sawshark object itself.
     */
    public Sawshark picture(byte[] picture) {
        this.picture = CoreUtils.clone(picture);
        return this;
    }
}