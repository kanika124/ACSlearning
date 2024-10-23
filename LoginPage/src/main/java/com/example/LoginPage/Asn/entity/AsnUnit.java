package com.example.LoginPage.Asn.entity;

import com.example.LoginPage.product.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsnUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer unitId;

    private Integer quantity;

    private Long receivedQty;

    private String location;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public AsnUnit(Integer quantity, Long receivedQty, String location, Product product) {
        this.quantity = quantity;
        this.receivedQty = receivedQty;
        this.location = location;
        this.product = product;

    }
}
