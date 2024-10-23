package com.example.LoginPage.Asn.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class NoticeInfoDto {
    private String poNumber;
    private String lotNumber;
    private Integer totalQuantity;
    private LocalDate createdOn;
    private Long createdBy;
    private Long warehouseId;
    private List<NoticeUnitDto> asnUnitList;

}
