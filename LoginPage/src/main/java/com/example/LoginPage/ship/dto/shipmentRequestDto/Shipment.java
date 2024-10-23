
package com.example.LoginPage.ship.dto.shipmentRequestDto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "external_reference",
    "address_to",
    "address_from",
    "parcels"
})
@Generated("jsonschema2pojo")
public class Shipment {

    @JsonProperty("external_reference")
    private String externalReference;
    @JsonProperty("address_to")
    private AddressTo addressTo;
    @JsonProperty("address_from")
    private AddressFrom addressFrom;
    @JsonProperty("parcels")
    private List<Parcel> parcels;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("external_reference")
    public String getExternalReference() {
        return externalReference;
    }

    @JsonProperty("external_reference")
    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

    @JsonProperty("address_to")
    public AddressTo getAddressTo() {
        return addressTo;
    }

    @JsonProperty("address_to")
    public void setAddressTo(AddressTo addressTo) {
        this.addressTo = addressTo;
    }

    @JsonProperty("address_from")
    public AddressFrom getAddressFrom() {
        return addressFrom;
    }

    @JsonProperty("address_from")
    public void setAddressFrom(AddressFrom addressFrom) {
        this.addressFrom = addressFrom;
    }

    @JsonProperty("parcels")
    public List<Parcel> getParcels() {
        return parcels;
    }

    @JsonProperty("parcels")
    public void setParcels(List<Parcel> parcels) {
        this.parcels = parcels;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
