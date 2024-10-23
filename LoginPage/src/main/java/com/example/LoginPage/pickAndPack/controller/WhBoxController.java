package com.example.LoginPage.pickAndPack.controller;

import com.example.LoginPage.pickAndPack.dto.AssignedPickerDto;
import com.example.LoginPage.pickAndPack.dto.BoxDto;
import com.example.LoginPage.pickAndPack.dto.OrderContainerDto;
import com.example.LoginPage.pickAndPack.entity.AssignedPicker;
import com.example.LoginPage.pickAndPack.impl.WhBoxImpl;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/box")
public class WhBoxController {
    @Autowired
    WhBoxImpl whBoxImpl;

    @PostMapping("/print")
    public ResponseEntity<?> printBoxLabel(@RequestBody BoxDto boxDto){

        List<Long> result = whBoxImpl.printBoxLabel(boxDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/getOrders")
    public ResponseEntity<?> orderId(@RequestParam String orderId){
        List<Long> getIds = whBoxImpl.getProductIdsByOrderId(orderId);
        return new ResponseEntity<>(getIds,HttpStatus.OK);
    }

    @PostMapping("/assignPicker")
    public ResponseEntity<?> assignPicker(@RequestBody AssignedPickerDto assignedPickerDto){
        return whBoxImpl.assignPicker(assignedPickerDto);
    }
    @PostMapping("/submit")
    public ResponseEntity<?> submitPickList(@RequestBody OrderContainerDto orderContainerDto){

        return whBoxImpl.submitPickList(orderContainerDto);
    }
    // ASSIGNMENT
    @GetMapping("/getRandomContainerId")
    public String getRandomContainerNumber(){
        return whBoxImpl.getRandomContainerNumber();
    }
    @GetMapping("/getOrderDetails")
    public ResponseEntity<?> scanContainer(@RequestParam String containerId){
        return null;
    }
}
