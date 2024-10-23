package com.example.LoginPage.client.dto;

import lombok.Data;

@Data
public class ClientDto {
    private String clientName;
    private String clientEmail;
    private Long phoneNum;
    private String address1;
    private Long cityId;
    private Long userId;
}
