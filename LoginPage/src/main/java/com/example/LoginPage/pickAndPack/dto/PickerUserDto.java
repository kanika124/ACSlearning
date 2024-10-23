package com.example.LoginPage.pickAndPack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PickerUserDto {
    private String containerId;
    private Long productId;
    private Integer quantity;
}
