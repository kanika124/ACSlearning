package com.example.LoginPage.repot.reportss;

import com.example.LoginPage.product.entity.Product;
import com.example.LoginPage.product.repo.ProductRepo;
import com.example.LoginPage.user.entity.User;
import com.example.LoginPage.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductReport {
        @Autowired
        private ProductRepo proRepo;
        public Map<String,List<String>> MyListMap(Map<String, List<String>> map){
            Map<String, List<String>> map1=new HashMap<>();
            List<Product> listproduct=proRepo.findAll();
            List<String> ls=new ArrayList<>();
            List<String> ls1=new ArrayList<>();

            for (Map.Entry<String,List<String>> entry : map.entrySet()) {
                if(entry.getValue().equals("name")) {
                    for (int i = 0; i < listproduct.size(); i++) {
                        ls.add(listproduct.get(i).getProductName());
                    }
                    map1.put(String.valueOf(entry.getValue()),ls);
                } else if (entry.getValue().equals("sku")) {
                    for(int i=0;i<listproduct.size();i++){
                        ls1.add(String.valueOf(listproduct.get(i).getSku()));
                    }
                    map1.put(String.valueOf(entry.getValue()),ls1);
                }else if (entry.getValue().equals("upc")) {
                    for(int i=0;i<listproduct.size();i++){
                        ls1.add(String.valueOf(listproduct.get(i).getUpc()));
                    }
                    map1.put(String.valueOf(entry.getValue()),ls1);
                }


            }

            return map1;

        }

    }
