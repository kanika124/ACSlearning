package com.example.LoginPage.product.repo;

import com.example.LoginPage.product.entity.ProductAttributeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAttributeRepo extends JpaRepository<ProductAttributeInfo,Long> {
}
