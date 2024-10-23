package com.example.LoginPage.repot.repo;

import com.example.LoginPage.repot.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepo extends JpaRepository<Report,Integer> {
}
