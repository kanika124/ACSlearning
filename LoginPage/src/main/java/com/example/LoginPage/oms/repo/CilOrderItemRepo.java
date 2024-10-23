package com.example.LoginPage.oms.repo;

import com.example.LoginPage.oms.entity.CilOrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CilOrderItemRepo extends JpaRepository<CilOrderItems, Long> {
}
