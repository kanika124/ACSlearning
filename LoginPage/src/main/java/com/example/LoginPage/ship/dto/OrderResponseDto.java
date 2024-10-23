package com.example.LoginPage.ship.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
        private String orderNumber;
        private String carrier;
        private String serviceType;
        private String shipToName;
        private String shipToAddress1;
        private String shipToAddress2;
        private String shipToCity;
        private String shipToState;
        private String shipToCountry;
        private Integer shipToPostalCode;
        private Boolean shipToIsResidential;



        private String shipFromName;
        private String shipFromAddress1;
        private String shipFromAddress2;
        private String shipFromCity;
        private String shipFromState;
        private String shipFromCountry;
        private Integer shipFromPostalCode;
        private Boolean shipFromIsResidential;

}
