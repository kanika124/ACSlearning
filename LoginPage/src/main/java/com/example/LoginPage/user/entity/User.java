package com.example.LoginPage.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private Long phoneNo;
    private String password;
    @ElementCollection
    private List<Long> clientId = new ArrayList<>();
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Role role;
}
