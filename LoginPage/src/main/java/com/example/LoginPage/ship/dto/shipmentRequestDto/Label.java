package com.example.LoginPage.ship.dto.shipmentRequestDto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Label {
    private Integer id;
    @JsonProperty("label_url")
    private String labelUrl;
    private Double cost;
    @JsonProperty("final_mile_carrier")
    private String finalMileCarrier;
    @JsonProperty("tracking_url")
    private String trackingUrl;
    @JsonProperty("tracking_number")
    private String trackingNumber;
    @JsonProperty("label_format")
    private String labelFormat;
    private final static long serialVersionUID = -5774031953515526570L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabelUrl() {
        return labelUrl;
    }

    public void setLabelUrl(String labelUrl) {
        this.labelUrl = labelUrl;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getFinalMileCarrier() {
        return finalMileCarrier;
    }

    public void setFinalMileCarrier(String finalMileCarrier) {
        this.finalMileCarrier = finalMileCarrier;
    }

    public String getTrackingUrl() {
        return trackingUrl;
    }

    public void setTrackingUrl(String trackingUrl) {
        this.trackingUrl = trackingUrl;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getLabelFormat() {
        return labelFormat;
    }

    public void setLabelFormat(String labelFormat) {
        this.labelFormat = labelFormat;
    }

}
