package com.example.LoginPage.truckLoad.implementation;

import com.example.LoginPage.oms.entity.FepOrdersInfo;
import com.example.LoginPage.oms.enumm.OrderStatus;
import com.example.LoginPage.oms.repo.FepOrderInfoRepo;
import com.example.LoginPage.truckLoad.entity.LisencePlate;
import com.example.LoginPage.truckLoad.lpnDto.LpnDto;
import com.example.LoginPage.truckLoad.repo.LisencePlateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TruckLoadImpl {
    @Autowired
    FepOrderInfoRepo fepOrderInfoRepo;
    @Autowired
    LisencePlateRepo lisencePlateRepo;

    public ResponseEntity<List<FepOrdersInfo>> getAllReadyToShip(){
        List<FepOrdersInfo> fepOrdersInfo = fepOrderInfoRepo.findByStatus(OrderStatus.READYTOSHIP);
        if (fepOrdersInfo.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(fepOrdersInfo);
    }

    public ResponseEntity<List<LisencePlate>> createLpn(LpnDto lpnDto) {
        List<LisencePlate> lisencePlateList = new ArrayList<>();
        for(String orderId : lpnDto.getOrderId()){
            Optional<FepOrdersInfo> optionalFepOrdersInfo=fepOrderInfoRepo.findByOrderId(orderId);
            if(optionalFepOrdersInfo.isPresent()){
                FepOrdersInfo ordersInfo = optionalFepOrdersInfo.get();
                if(ordersInfo.getStatus().equals(OrderStatus.READYTOSHIP)){
                    LisencePlate lisencePlate = new LisencePlate();
                    lisencePlate.setLpnNo(lpnDto.getLpnNo());
                    lisencePlate.setOrderId(lisencePlate.getOrderId());
                    lisencePlateList.add(lisencePlateRepo.save(lisencePlate));
                }
            }
        }
        return new ResponseEntity<>(lisencePlateList, HttpStatus.CREATED);
    }
}


