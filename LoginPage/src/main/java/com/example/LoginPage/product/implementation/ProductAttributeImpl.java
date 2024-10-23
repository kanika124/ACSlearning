package com.example.LoginPage.product.implementation;

import com.example.LoginPage.product.entity.Product;
import com.example.LoginPage.product.entity.ProductAttributeInfo;
import com.example.LoginPage.product.repo.ProductAttributeRepo;
import com.example.LoginPage.product.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductAttributeImpl {
    @Autowired
    ProductAttributeRepo productAttributeInfo;
    @Autowired
    ProductRepo productRepo;
    public void addInfo(ProductAttributeInfo productAttribute){

        ProductAttributeInfo attributeInfo = productAttributeInfo.save(productAttribute);

        Product currProduct = productRepo.findById(productAttribute.getProductId()).get();

        currProduct.getAttributeInfoId().add(attributeInfo.getId());
    }

}
