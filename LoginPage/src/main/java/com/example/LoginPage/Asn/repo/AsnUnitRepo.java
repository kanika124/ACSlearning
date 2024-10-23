package com.example.LoginPage.Asn.repo;

import com.example.LoginPage.Asn.entity.AsnUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsnUnitRepo extends JpaRepository<AsnUnit,Long> {
}
