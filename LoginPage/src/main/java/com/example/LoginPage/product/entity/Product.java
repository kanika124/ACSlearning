package com.example.LoginPage.product.entity;

import com.example.LoginPage.product.enumm.ProductCategory;
import com.example.LoginPage.product.enumm.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    private String productName;
    private String sku;
    private String upc;
    private String description;
    private int createdBy;
    private String creationDate;
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ElementCollection
    private List<Long> attributeInfoId = new ArrayList<>();
    private Long clientId;

}
