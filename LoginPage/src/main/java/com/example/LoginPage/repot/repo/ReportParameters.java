package com.example.LoginPage.repot.repo;

import com.example.LoginPage.repot.entity.ReportParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportParameters extends JpaRepository<ReportParameter,Integer> {
}
