package com.example.LoginPage.address.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long countryId;
    private String countryName;
    private String shortForm;
    @OneToMany(mappedBy = "country",cascade = CascadeType.ALL)
    private List<State> state;
}
