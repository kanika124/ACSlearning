package com.example.LoginPage.address.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cityId;
    private String cityName;
    private String shortForm;
    @ManyToOne
    @JoinColumn
    private State state;
//    @OneToMany(mappedBy = "city",cascade = CascadeType.ALL)
//    private List<Client> client;
}
