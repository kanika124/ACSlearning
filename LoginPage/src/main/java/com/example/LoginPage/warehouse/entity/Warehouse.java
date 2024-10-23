package com.example.LoginPage.warehouse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String warehouseName;
    private String marketDesc;
    private String shipFromName;
    private String shipFromAddress1;
    private String shipFromAddress2;
    private Integer shipFromPostalCode;
    private Boolean shipFromIsResidential;
    private String shipFromCountry;
    private String shipFromCity;
    private String shipFromState;
}
