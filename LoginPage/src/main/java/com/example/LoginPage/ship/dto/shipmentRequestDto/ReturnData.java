package com.example.LoginPage.ship.dto.shipmentRequestDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnData {
        @JsonProperty("shipment_id")
        private Integer shipmentId;
        @JsonProperty("rate_id")
        private Integer rateId;
        @JsonProperty("shipment_tracking_number")
        private String shipmentTrackingNumber;
        private String confirmation;
        private List<Label> labels;
}
