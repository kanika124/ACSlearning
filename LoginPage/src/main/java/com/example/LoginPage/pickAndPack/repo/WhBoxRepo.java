package com.example.LoginPage.pickAndPack.repo;

import com.example.LoginPage.pickAndPack.entity.WhBoxLabel;
import com.example.LoginPage.pickAndPack.enumm.BoxType;
import com.example.LoginPage.product.enumm.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WhBoxRepo extends JpaRepository<WhBoxLabel,Long> {
    List<WhBoxLabel> findByWarehouseIdAndBoxTypeAndStatus(Long warehouseId, BoxType boxType, Status status);
}
