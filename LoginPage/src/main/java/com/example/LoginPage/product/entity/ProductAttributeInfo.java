package com.example.LoginPage.product.entity;



import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class ProductAttributeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long productId;
    private Long attributeId;
    private String attributeDesc;
}
