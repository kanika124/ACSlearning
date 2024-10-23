package com.example.LoginPage.pickAndPack.entity;

import com.example.LoginPage.pickAndPack.enumm.BoxType;
import com.example.LoginPage.product.enumm.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WhBoxLabel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long warehouseId;
    @Enumerated(value = EnumType.STRING)
    private BoxType boxType;
    @Enumerated(value = EnumType.STRING)
    private Status status;

}
