
package com.example.LoginPage.ship.dto.shipmentRequestDto;

import java.util.LinkedHashMap;
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
    "name",
    "company",
    "is_residential",
    "street1",
    "city",
    "state",
    "country",
    "postal_code"
})
@Generated("jsonschema2pojo")
public class AddressTo {

    @JsonProperty("name")
    private String name;
    @JsonProperty("company")
    private String company;
    @JsonProperty("is_residential")
    private Boolean isResidential;
    @JsonProperty("street1")
    private String street1;
    @JsonProperty("street2")
    private String street2;

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("country")
    private String country;
    @JsonProperty("postal_code")
    private Integer postalCode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("company")
    public String getCompany() {
        return company;
    }

    @JsonProperty("company")
    public void setCompany(String company) {
        this.company = company;
    }

    @JsonProperty("is_residential")
    public Boolean getIsResidential() {
        return isResidential;
    }

    @JsonProperty("is_residential")
    public void setIsResidential(Boolean isResidential) {
        this.isResidential = isResidential;
    }

    @JsonProperty("street1")
    public String getStreet1() {
        return street1;
    }

    @JsonProperty("street1")
    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("postal_code")
    public Integer getPostalCode() {
        return postalCode;
    }

    @JsonProperty("postal_code")
    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
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
