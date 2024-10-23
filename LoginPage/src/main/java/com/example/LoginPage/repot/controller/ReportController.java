package com.example.LoginPage.repot.controller;

import com.example.LoginPage.repot.entity.Report;
import com.example.LoginPage.repot.repo.ReportRepo;
import com.example.LoginPage.repot.service.Reportservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    ReportRepo reportRepo;
    @Autowired
    Reportservice reportservice;

    @PostMapping("/add")
    public void addParameter(@RequestBody Report report){
        reportRepo.save(report);
    }
}
