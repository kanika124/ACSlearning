package com.example.LoginPage.oms.repo;

import com.example.LoginPage.oms.entity.FepOrdersItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FepOrderItemRepo extends JpaRepository<FepOrdersItems, Long> {
}
