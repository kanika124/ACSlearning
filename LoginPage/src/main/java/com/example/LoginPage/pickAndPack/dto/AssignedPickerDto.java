package com.example.LoginPage.pickAndPack.dto;

import com.example.LoginPage.product.enumm.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Getter
public class AssignedPickerDto {

    private String pickerName;
    private String orderId;
    private String boxId;

}
