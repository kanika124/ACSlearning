package com.example.LoginPage.truckLoad.controller;

import com.example.LoginPage.truckLoad.entity.LisencePlate;
import com.example.LoginPage.truckLoad.implementation.TruckLoadImpl;
import com.example.LoginPage.truckLoad.lpnDto.LpnDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/truck")
public class TruckLoadController {
    @Autowired
    TruckLoadImpl truckLoad;
    @GetMapping("/readyToShip")
    public ResponseEntity<?> getAllReadyToShip(){
        return truckLoad.getAllReadyToShip();
    }

    @PostMapping("/createLpn")
    public ResponseEntity<LisencePlate> createLicensePlate(@RequestBody LpnDto lpnDto) {
        LisencePlate licensePlate = truckLoad.createLpn(lpnDto);
        return ResponseEntity.ok(licensePlate);
    }
}
