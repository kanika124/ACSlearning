package com.example.LoginPage.truckLoad.entity;

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
public class Manifest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String manifestNo;
    private  String orderId;
    private String trackingLpnId;
    private String shipTo;
    private String shipFrom;
    private Double weight;
    private String carrierName;

    private String lpnNo;

}
