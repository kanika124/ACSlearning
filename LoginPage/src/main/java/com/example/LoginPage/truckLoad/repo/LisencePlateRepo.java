package com.example.LoginPage.truckLoad.repo;

import com.example.LoginPage.truckLoad.entity.LisencePlate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LisencePlateRepo extends JpaRepository<LisencePlate,Long> {
    Optional<LisencePlate> findBylpnNo(String lpnNo);
}
