package com.example.LoginPage.ship.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScanBoxLabelDto {
    private String orderId;
    private String boxLabel;
    private Double boxWeight;
    private BoxDimensionDto boxDimension;

}
