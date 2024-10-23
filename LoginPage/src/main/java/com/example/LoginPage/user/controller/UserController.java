package com.example.LoginPage.user.controller;

import com.example.LoginPage.user.dto.UserDto;
//import com.example.LoginPage.entity.Login;
import com.example.LoginPage.user.entity.User;
import com.example.LoginPage.user.impl.UserImpl;
import com.example.LoginPage.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserImpl userImpl;
    @Autowired
    UserRepo userRepo;

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto) {

        return userImpl.addUser(userDto);
    }

    public List<User> getUser(){
        return userImpl.getAllUser();
    }

    @GetMapping("/login")
    public List<Object> login(@RequestParam("userId") Long userId, @RequestParam("password") String password){
        User currUser = userRepo.findById(userId).get();
       // Map<String,Object> userData = new HashMap<>();
        List<Object> list=new ArrayList<>();

        if (currUser.getPassword().equals(password)){
           /* userData.put("Role",currUser.getRole());
            userData.put("Message","Login Successful");
            userData.put("Name",currUser.getName());*/

            list.add("Login SuccessFull");
            list.add(currUser.getName());
            list.add(currUser.getRole());

            return list;

        } else {
            /*userData.put("Message","Invalid Credentials");
            return userData;*/
            list.add("Invalid Credentials");
            return list;
        }

    }




}
