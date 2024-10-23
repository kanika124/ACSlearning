package com.example.LoginPage.user.repo;

import com.example.LoginPage.user.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepo extends JpaRepository<Permission, Long> {
}
