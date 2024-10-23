package com.example.LoginPage.oms.repo;

import com.example.LoginPage.oms.entity.FepOrdersInfo;
import com.example.LoginPage.oms.enumm.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FepOrderInfoRepo extends JpaRepository<FepOrdersInfo, Long> {
    Optional<FepOrdersInfo> findByOrderId(String orderId);
    List<FepOrdersInfo> findByStatus(OrderStatus status);

}
