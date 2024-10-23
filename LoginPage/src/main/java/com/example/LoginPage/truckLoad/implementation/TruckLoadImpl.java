package com.example.LoginPage.truckLoad.implementation;

import com.example.LoginPage.oms.entity.FepOrdersInfo;
import com.example.LoginPage.oms.enumm.OrderStatus;
import com.example.LoginPage.oms.repo.FepOrderInfoRepo;
import com.example.LoginPage.truckLoad.entity.LisencePlate;
import com.example.LoginPage.truckLoad.lpnDto.LpnDto;
import com.example.LoginPage.truckLoad.repo.LisencePlateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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




    public LisencePlate createLpn(LpnDto lpnDto) {

        List<String> orderIds = lpnDto.getOrderId();
        if (orderIds == null || orderIds.isEmpty()) {
            throw new IllegalArgumentException("Order IDs must not be empty");
        }

        LisencePlate licensePlate = new LisencePlate();
        licensePlate.setLpnNo(lpnDto.getLpnNo());
        licensePlate.setOrderId(String.valueOf(orderIds));



        return lisencePlateRepo.save(licensePlate);
    }
}


