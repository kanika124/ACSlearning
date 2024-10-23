package com.example.LoginPage.product.implementation;

import com.example.LoginPage.product.entity.Attribute;
import com.example.LoginPage.product.repo.AttributeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AttributeImpl {

    @Autowired
    AttributeRepo attributeRepo;

    public void addAttribute(Attribute attribute){
        attributeRepo.save(attribute);
    }
}


