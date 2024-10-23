package com.example.LoginPage.repot.service;

import com.example.LoginPage.repot.repo.ReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Check {
    @Autowired
    private ReportRepo reportRepo;

    public boolean checkProduct(int id){
        return reportRepo.existsById(id);
    }
}
