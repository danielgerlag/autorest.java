package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * The BlobPrefix model.
 */
@JacksonXmlRootElement(localName = "BlobPrefix")
@Fluent
public final class BlobPrefix {
    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "Name", required = true)
    private String name;

    /**
     * Get the name property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param name the name value to set.
     * @return the BlobPrefix object itself.
     */
    public BlobPrefix setName(String name) {
        this.name = name;
        return this;
    }

    public void validate() {
        if (getName() == null) {
            throw new IllegalArgumentException("Missing required property name in model BlobPrefix");
        }
    }
}