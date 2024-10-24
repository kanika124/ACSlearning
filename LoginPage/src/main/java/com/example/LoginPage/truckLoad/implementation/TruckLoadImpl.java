package com.example.LoginPage.truckLoad.implementation;

import com.example.LoginPage.oms.entity.FepOrdersInfo;
import com.example.LoginPage.oms.enumm.OrderStatus;
import com.example.LoginPage.oms.repo.FepOrderInfoRepo;
import com.example.LoginPage.truckLoad.entity.LisencePlate;
import com.example.LoginPage.truckLoad.entity.Manifest;
import com.example.LoginPage.truckLoad.lpnDto.LpnDto;
import com.example.LoginPage.truckLoad.lpnDto.ManifestDto;
import com.example.LoginPage.truckLoad.repo.LisencePlateRepo;
import com.example.LoginPage.truckLoad.repo.ManifestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TruckLoadImpl {
    @Autowired
    FepOrderInfoRepo fepOrderInfoRepo;
    @Autowired
    LisencePlateRepo lisencePlateRepo;
    @Autowired
    ManifestRepo manifestRepo;

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
                    lisencePlate.setOrderId(orderId);
                    lisencePlateList.add(lisencePlateRepo.save(lisencePlate));
                }
            }
        }
        return new ResponseEntity<>(lisencePlateList, HttpStatus.CREATED);
    }


    public ResponseEntity<List<Manifest>> createManifest(ManifestDto manifestDto){
        List<Manifest> manifestList = new ArrayList<>();
        for(String lpNo: manifestDto.getLpnNo()){
            Optional<LisencePlate> lisencePlate = lisencePlateRepo.findBylpnNo(lpNo);
            if(lisencePlate.isPresent()){
                Manifest manifest = new Manifest();
                manifest.setManifestNo(manifestDto.getManifestNo());
                manifest.setLpnNo(lpNo);
                System.out.println("hie");
                manifestList.add(manifestRepo.save(manifest));
                System.out.println("hello");
            }
            else {
                return new ResponseEntity<>(HttpStatus.valueOf("Not found that id"));
            }
        }
        return new ResponseEntity<>(manifestList,HttpStatus.CREATED);
    }

}


