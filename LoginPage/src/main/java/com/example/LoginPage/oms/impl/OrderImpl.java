package com.example.LoginPage.oms.impl;

import com.example.LoginPage.oms.entity.CilOrderInfo;
import com.example.LoginPage.oms.entity.CilOrderItems;
import com.example.LoginPage.oms.entity.FepOrdersInfo;
import com.example.LoginPage.oms.entity.FepOrdersItems;
import com.example.LoginPage.oms.enumm.OrderStatus;
import com.example.LoginPage.oms.repo.CilOrderInfoRepo;
import com.example.LoginPage.oms.repo.CilOrderItemRepo;
import com.example.LoginPage.oms.repo.FepOrderInfoRepo;
import com.example.LoginPage.warehouse.entity.WareHouseReceivedItems;
import com.example.LoginPage.warehouse.entity.Warehouse;
import com.example.LoginPage.warehouse.repo.WareHouseReceivedItemsRepo;
import com.example.LoginPage.warehouse.repo.WarehouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderImpl {
    @Autowired
    CilOrderInfoRepo cilOrderInfoRepo;
    @Autowired
    CilOrderItemRepo cilOrderItemRepo;
    @Autowired
    WareHouseReceivedItemsRepo wareHouseItemRepo;
    @Autowired
    FepOrderInfoRepo fepOrderInfoRepo;
    public ResponseEntity<?> createOrder(CilOrderInfo cilOrderInfo){

        List<CilOrderItems> items = cilOrderItemRepo.saveAll(cilOrderInfo.getOrderItemsList());
        cilOrderInfo.setOrderItemsList(items);
        CilOrderInfo order = cilOrderInfoRepo.save(cilOrderInfo);
        List<CilOrderItems> cilOrderItems = cilOrderInfo.getOrderItemsList();

        cilOrderInfo.setStatus(OrderStatus.CREATED);


        //For Fep layer
        FepOrdersInfo fepOrdersInfo = new FepOrdersInfo();
        fepOrdersInfo.setStatus(OrderStatus.CREATED);
        fepOrdersInfo.setOrderId(cilOrderInfo.getOrderId());
        fepOrdersInfo.setClientId(cilOrderInfo.getClientId());

        fepOrdersInfo.setCarrierType(cilOrderInfo.getCarrierType());
        fepOrdersInfo.setServiceType(cilOrderInfo.getServiceType());
        fepOrdersInfo.setShipToAddress1(cilOrderInfo.getShipToAddress1());
        fepOrdersInfo.setShipToAddress2(cilOrderInfo.getShipToAddress2());
        fepOrdersInfo.setShipToName(cilOrderInfo.getShipToName());
        fepOrdersInfo.setShipToPostalCode(cilOrderInfo.getShipToPostalCode());
        fepOrdersInfo.setShipToCountry(cilOrderInfo.getShipToCountry());
        fepOrdersInfo.setShipToCity(cilOrderInfo.getShipToCity());
        fepOrdersInfo.setShipToState(cilOrderInfo.getShipToState());
        fepOrdersInfo.setShipToIsResidential(cilOrderInfo.getShipToIsResidential());
        fepOrdersInfo.setWarehouseId(cilOrderInfo.getWarehouseId());


        List<FepOrdersItems> fepOrdersItemsList = new ArrayList<>();

        for (CilOrderItems item : cilOrderItems){

            WareHouseReceivedItems wareHouseItem = wareHouseItemRepo.findByProductId(item.getProductId());
            if (wareHouseItem == null) {
                return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
            }
            FepOrdersItems fepOrdersItem = new FepOrdersItems();

            if(wareHouseItem.getQuantity() > 0 && wareHouseItem.getQuantity() >= item.getProductQty()){
                wareHouseItem.setQuantity(wareHouseItem.getQuantity() - item.getProductQty());

                fepOrdersItem.setProductQty(item.getProductQty());
                fepOrdersItem.setProductId(item.getProductId());
                fepOrdersItemsList.add(fepOrdersItem);
//                whOrderItemRepo.save(fepOrdersItem);

                System.out.println("in end of iff");


            } else if(wareHouseItem.getQuantity() < item.getProductQty()) {

                fepOrdersItem.setProductQty(wareHouseItem.getQuantity());
                fepOrdersItem.setProductId(wareHouseItem.getQuantity());
                fepOrdersItemsList.add(fepOrdersItem);

                wareHouseItem.setQuantity(0L);
                System.out.println("hii");
                cilOrderInfo.setStatus(OrderStatus.valueOf(String.valueOf(OrderStatus.BACKORDER)));
                fepOrdersInfo.setStatus(OrderStatus.BACKORDER);
//                whOrderItemRepo.save(fepOrdersItem);
            }

//            cilOrderItemRepo.save(item);
            wareHouseItemRepo.save(wareHouseItem);
        }
        fepOrdersInfo.setOrderItemsList(fepOrdersItemsList);

//        cilOrderInfoRepo.save(new CilOrderInfo());
        fepOrderInfoRepo.save(fepOrdersInfo);

        return  new ResponseEntity<>(cilOrderInfo, HttpStatus.CREATED);
    }
}
