package com.example.LoginPage.warehouse.repo;

import com.example.LoginPage.warehouse.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarehouseRepo extends JpaRepository<Warehouse,Long> {
    Optional<Warehouse> findById(Long warehouseId);

//    Long findById();
}
