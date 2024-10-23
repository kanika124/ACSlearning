package com.example.LoginPage.product.repo;

import com.example.LoginPage.product.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepo extends JpaRepository<Attribute,Long> {
    Attribute findByAttributeId(Long attributeId);

}
