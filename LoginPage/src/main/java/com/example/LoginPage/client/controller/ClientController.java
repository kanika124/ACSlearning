package com.example.LoginPage.client.controller;

import com.example.LoginPage.client.dto.ClientDto;
import com.example.LoginPage.client.entity.Client;
import com.example.LoginPage.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping("/add")
    public ResponseEntity<Client> addClient(@RequestBody ClientDto clientDto){
        return clientService.addClient(clientDto);
    }
    @GetMapping("getUserById/{id}")
    public Map<String, Object> getUserById(@PathVariable Long id){
        return clientService.getUserById(id);
    }
}
