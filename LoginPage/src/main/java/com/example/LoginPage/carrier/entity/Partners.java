package com.example.LoginPage.carrier.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Partners {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String carrier;
    private String serviceType;

}
