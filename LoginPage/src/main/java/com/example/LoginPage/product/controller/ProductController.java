package com.example.LoginPage.product.controller;

import com.example.LoginPage.product.entity.Attribute;
import com.example.LoginPage.product.entity.Product;
import com.example.LoginPage.product.entity.ProductAttributeInfo;
import com.example.LoginPage.product.implementation.ProductAttributeImpl;
import com.example.LoginPage.product.implementation.AttributeImpl;
import com.example.LoginPage.product.implementation.ProductImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductImpl productImpl;
    @Autowired
    AttributeImpl attributeImpl;
    @Autowired
    ProductAttributeImpl productAttributeImpl;

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        return productImpl.addProduct(product);
    }

    @PostMapping("/addAttribute")
    public void addAttribute(@RequestBody Attribute attribute){
        attributeImpl.addAttribute(attribute);
    }

    @PostMapping("/addInfo")
    public void addInfo(@RequestBody ProductAttributeInfo productAttributeInfo){
        productAttributeImpl.addInfo(productAttributeInfo);
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<?> findProductBySKU(@PathVariable String sku) {
        Product products = productImpl.findProductBySku(sku);
        if (products==null) {
            return ResponseEntity.notFound().build();
        }
       return ResponseEntity.ok(products);
    }


    @GetMapping("/client/{cId}")
    public ResponseEntity<Object> getProductByClient(@PathVariable Long cId) {
        List<Product> products = productImpl.findProductByClientId(cId);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nothing for this client");
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/user/{uId}")
    public ResponseEntity<Object> findProductByUserId(@PathVariable Long uId) {
        List<Product> products = productImpl.findProductByUserId(uId);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nothing for this user");
        }
        return ResponseEntity.ok(products);
    }
}
