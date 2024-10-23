package com.example.LoginPage.product.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long attributeId;
    private String description;
}
