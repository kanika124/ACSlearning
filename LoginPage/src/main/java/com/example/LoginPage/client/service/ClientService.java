package com.example.LoginPage.client.service;

import com.example.LoginPage.client.dto.ClientDto;
import com.example.LoginPage.client.entity.Client;
import com.example.LoginPage.client.repo.CityRepo;
import com.example.LoginPage.client.repo.ClientRepo;
import com.example.LoginPage.user.entity.User;
import com.example.LoginPage.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ClientService {

    @Autowired
    CityRepo cityRepo;

    @Autowired
    ClientRepo clientRepo;
    @Autowired
    UserRepo userRepo;

    public ResponseEntity<Client> addClient(ClientDto clientDto){

        Client client = new Client();

        User user = userRepo.findById(clientDto.getUserId()).get();

        client.setClientName(clientDto.getClientName());
        client.setClientEmail(clientDto.getClientEmail());
        client.setPhoneNum(clientDto.getPhoneNum());
        client.setAddress1(clientDto.getAddress1());
        client.setUserId(user.getId());
        client.setCityId(clientDto.getCityId());
        client = clientRepo.save(client);

        user.getClientId().add(client.getId());
        userRepo.save(user);

        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    public Map<String, Object> getUserById(Long id){
        Client client = clientRepo.findById(id).get();

        Map<String, Object> response = new HashMap<>();

        response.put("id", client.getId());
        response.put("Name", client.getClientName());
        response.put("Phone", client.getPhoneNum());
        response.put("Email", client.getClientEmail());
        response.put("Address1", client.getAddress1());
//        response.put("City", client.getCity().getCityName());
//        response.put("State", client.getCity().getState().getStateName());
//        response.put("Country", client.getCity().getState().getCountry().getCountryName());

        System.out.println("Client Details:\n" +
                            "id: " + response.get("id") + "\n" +
                            "Name: " + response.get("Name") + "\n" +
                            "Phone: " + response.get("Phone") + "\n" +
                            "Email: " + response.get("Email") + "\n" +
                            "Address1: " + response.get("Address1")/* + "\n" +
                            "City: " + response.get("City") + "\n" +
                            "State: " + response.get("State") + "\n" +
                            "Country: " + response.get("Country")*/);

        return response;
    }
}
