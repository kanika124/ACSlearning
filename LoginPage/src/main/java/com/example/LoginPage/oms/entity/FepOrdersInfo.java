package com.example.LoginPage.oms.entity;

import com.example.LoginPage.oms.enumm.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FepOrdersInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String orderId;
    private Integer clientId;



    private String shipToName;
    private String shipToAddress1;
    private String shipToAddress2;
    private Integer shipToPostalCode;
    private Boolean shipToIsResidential;
    private String shipToCountry;
    private String shipToCity;
    private String shipToState;


    private String carrierType;
    private String serviceType;
    private Long warehouseId;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_fk", referencedColumnName = "id")
    private List<FepOrdersItems> orderItemsList;

}
