package com.example.LoginPage.user.impl;

import com.example.LoginPage.user.dto.UserDto;
import com.example.LoginPage.user.entity.Permission;
import com.example.LoginPage.user.entity.Role;
import com.example.LoginPage.user.entity.User;
import com.example.LoginPage.user.repo.PermissionRepo;
import com.example.LoginPage.user.repo.RoleRepo;
import com.example.LoginPage.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImpl {
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    PermissionRepo permissionRepo;
    public ResponseEntity<User> addUser(UserDto userDto) {
        User newUser = new User();
        newUser.setName(userDto.getName());
        newUser.setEmail(userDto.getEmail());
        newUser.setPhoneNo(userDto.getPhone());
        newUser.setPassword(userDto.getPassword());

        Role role = new Role();
        role.setRoleName(userDto.getRoleName());
        role.setDescription(userDto.getDescription());
        role.setStatus(userDto.isStatus());
        role.setUser(newUser);
        newUser.setRole(role);

        Permission permission = new Permission();
        permission.setReadPermission(userDto.isReadPermission());
        permission.setWritePermission(userDto.isWritePermission());
        permission.setUpdatePermission(userDto.isUpdatePermission());

        role.setPermission(permission);
        permission.setRole(role);
        userRepo.save(newUser);


        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    public List<User> getAllUser() {
        return null;
    }
}
