package com.example.LoginPage.warehouse.repo;

import com.example.LoginPage.warehouse.entity.WareHouseReceivedItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WareHouseReceivedItemsRepo extends JpaRepository<WareHouseReceivedItems,Long> {

    WareHouseReceivedItems findByProductId(Long productId);
}
