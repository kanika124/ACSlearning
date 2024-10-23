package com.example.LoginPage.pickAndPack.dto;

import com.example.LoginPage.pickAndPack.enumm.PickerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderContainerDto {
    private String orderId;
    private String pickerName;
    private String boxId;
    private List<PickerUserDto> pickerUserDtoList;
}
