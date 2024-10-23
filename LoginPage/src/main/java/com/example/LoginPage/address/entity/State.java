package com.example.LoginPage.address.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stateId;
    private String stateName;
    private String shortForm;
    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL)
    private List<City> city;
    @ManyToOne
    @JoinColumn
    private Country country;
}
