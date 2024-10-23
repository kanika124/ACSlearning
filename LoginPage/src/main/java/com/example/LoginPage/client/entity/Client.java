package com.example.LoginPage.client.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String clientName;
    private String clientEmail;
    private Long phoneNum;
    private String address1;
    private Long cityId;
    private Long userId;
    @ElementCollection
    private List<Long> productId;

}
