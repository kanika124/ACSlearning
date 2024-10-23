package com.example.LoginPage.oms.repo;

import com.example.LoginPage.oms.entity.CilOrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface CilOrderInfoRepo extends JpaRepository<CilOrderInfo,Long> {
    Optional<CilOrderInfo> findByOrderId(String orderId);

}
