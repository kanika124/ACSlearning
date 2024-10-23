package com.example.LoginPage.warehouse.entity;

import com.example.LoginPage.warehouse.enumm.InventoryStage;
import com.example.LoginPage.warehouse.enumm.ReceiveStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Data
public class WareHouseReceivedItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long productId;
    private Long warehouseId;
    private Long clientId;
    private String locationBarcode;
    private Long quantity;
    private String lotNumber;
    private Long userId;
    private LocalDateTime createdOn;
    @Enumerated(EnumType.STRING)
    private ReceiveStatus receiveStatus;
    @Enumerated(EnumType.STRING)
    private InventoryStage inventoryStage;

}
