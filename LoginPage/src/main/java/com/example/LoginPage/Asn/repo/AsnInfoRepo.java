package com.example.LoginPage.Asn.repo;

import com.example.LoginPage.Asn.entity.AsnInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsnInfoRepo extends JpaRepository<AsnInfo,Long> {
}
