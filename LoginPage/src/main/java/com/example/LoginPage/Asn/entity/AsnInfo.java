package com.example.LoginPage.Asn.entity;

import com.example.LoginPage.client.entity.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsnInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "asn_id")
    private Integer asnId;

    private String poNumber;

    private String lotNumber;

    private Integer totalQuantity;

    private LocalDate createdOn;

    @ManyToOne
    @JoinColumn
    private Client client;

    //for one to many join use this to make a fk constraint in the joined table and donot add column name in joined table
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_asn_info_id", referencedColumnName = "asn_id")
    private List<AsnUnit> asnUnitsList;

}
