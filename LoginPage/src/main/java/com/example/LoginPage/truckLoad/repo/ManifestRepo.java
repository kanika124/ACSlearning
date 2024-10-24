package com.example.LoginPage.truckLoad.repo;

import com.example.LoginPage.truckLoad.entity.Manifest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManifestRepo extends JpaRepository<Manifest,Integer> {
}
