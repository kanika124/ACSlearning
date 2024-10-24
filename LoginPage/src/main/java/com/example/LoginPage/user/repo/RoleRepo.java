package com.example.LoginPage.user.repo;

import com.example.LoginPage.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);
}
