package com.example.LoginPage.product.repo;

import com.example.LoginPage.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    Product findBySku(String sku);
    List<Product> findAllByClientId(Long cId);
//    List<Product> findByUserId(Long uId);


}
