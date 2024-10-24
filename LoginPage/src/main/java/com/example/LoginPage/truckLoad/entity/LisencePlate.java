package com.example.LoginPage.truckLoad.entity;

import com.example.LoginPage.oms.entity.FepOrdersInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LisencePlate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String lId;
    private String lpnNo;
    private String orderId;
}
