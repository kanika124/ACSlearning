package com.example.LoginPage.product.implementation;

import com.example.LoginPage.client.repo.ClientRepo;
import com.example.LoginPage.client.entity.Client;
import com.example.LoginPage.product.entity.Product;
import com.example.LoginPage.product.repo.ProductRepo;
import com.example.LoginPage.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductImpl{
    @Autowired
    ProductRepo productRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    ClientRepo clientRepo;

    public ResponseEntity<Product> addProduct(Product product){
        Client currClient = clientRepo.findById(product.getClientId()).get();

        Product currProduct = productRepo.save(product);

        currClient.getProductId().add(currProduct.getProductId());
        clientRepo.save(currClient);

        return ResponseEntity.ok().body(currProduct);
    }
    public Product findProductBySku(String sku){
        return productRepo.findBySku(sku);
    }
    public List<Product> findProductByClientId(Long cId){
        return productRepo.findAllByClientId(cId);
    }

    public List<Product> findProductByUserId(Long userId){
        List<Long> clientIdList = userRepo.findClientIdByUserId(userId);

        List<Long> productIdList = new ArrayList<>();

        for (Long id : clientIdList){
            productIdList.addAll(clientRepo.findProductIdsByClientId(id));
        }

        return productRepo.findAllById(productIdList);
    }

}
