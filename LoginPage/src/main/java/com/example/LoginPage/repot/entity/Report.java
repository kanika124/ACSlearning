package com.example.LoginPage.repot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Report {
    @Id
    private Integer id;
    private String name;
    private String createdOn;
    private String status;
    List<Long> reportParameterId;
}
