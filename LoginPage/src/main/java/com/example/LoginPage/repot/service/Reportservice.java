package com.example.LoginPage.repot.service;

import com.example.LoginPage.client.repo.ClientRepo;
import com.example.LoginPage.product.repo.ProductRepo;
import com.example.LoginPage.repot.entity.Report;
import com.example.LoginPage.repot.entity.ReportParameter;
import com.example.LoginPage.repot.repo.ReportRepo;
import com.example.LoginPage.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class Reportservice {
    @Autowired
    ReportRepo reportRepo;
    @Autowired
    UserReport userReport;
    @Autowired
    UserRepo userRepo;
    @Autowired
    ProductReport proReport;
    @Autowired
    Check check;
    @Autowired
    ClientRepo clientRepo;
    @Autowired
    ReportParameter reportParameter;

}
