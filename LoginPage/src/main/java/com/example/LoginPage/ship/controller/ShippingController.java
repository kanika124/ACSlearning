package com.example.LoginPage.ship.controller;

import com.example.LoginPage.ship.dto.ScanBoxLabelDto;
import com.example.LoginPage.ship.dto.shipmentRequestDto.Shipment;
import com.example.LoginPage.ship.impl.ShippingImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/ship")
public class ShippingController {
    @Autowired
    ShippingImpl shippingImp;

    @GetMapping("/getBoxLabel")
    public ResponseEntity<?> generateShippingLabel(@RequestParam String boxId){
        return shippingImp.generateShippingLabel(boxId);
    }

    @PostMapping("/setDimensionAndWeight")
    public ResponseEntity<?> saveDimensionAndWeight(@RequestBody ScanBoxLabelDto scanBoxLabelDto){
        return shippingImp.saveDimensionAndWeight(scanBoxLabelDto);
    }

  /*  @GetMapping("/getShippingLabel")
    public ResponseEntity<?> getShippingLabel() {
        return shippingImp.getShippingLabel();
    }*/



//    @GetMapping("/getBoxDetails")
//    public ResponseEntity<OrderResponseDto> generateShippingLabel(@RequestParam String boxId) {
//        OrderResponseDto orderResponse = shippingImp.generateShippingLabel(boxId);
//        if (orderResponse != null) {
//            return ResponseEntity.ok(orderResponse);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
