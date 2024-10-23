package com.example.LoginPage.Asn.impl;

import com.example.LoginPage.Asn.dto.NoticeInfoDto;
import com.example.LoginPage.Asn.dto.NoticeUnitDto;
import com.example.LoginPage.Asn.entity.AsnInfo;
import com.example.LoginPage.Asn.entity.AsnUnit;
import com.example.LoginPage.Asn.repo.AsnInfoRepo;
import com.example.LoginPage.Asn.repo.AsnUnitRepo;
import com.example.LoginPage.client.repo.ClientRepo;
import com.example.LoginPage.product.repo.ProductRepo;
import com.example.LoginPage.warehouse.entity.WareHouseReceivedItems;
import com.example.LoginPage.warehouse.enumm.InventoryStage;
import com.example.LoginPage.warehouse.enumm.ReceiveStatus;
import com.example.LoginPage.warehouse.repo.WareHouseReceivedItemsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AsnImpl {
    @Autowired
    WareHouseReceivedItemsRepo wareHouseReceivedRepo;
    @Autowired
    ClientRepo clientRepo;
    @Autowired
    AsnInfoRepo asnInfoRepo;
    @Autowired
    ProductRepo productRepo;
    public AsnInfo createAsn(@RequestBody NoticeInfoDto noticeInfo){
            AsnInfo advShipNoticeInfo = new AsnInfo();
            advShipNoticeInfo.setCreatedOn(LocalDate.now());
            advShipNoticeInfo.setPoNumber(noticeInfo.getPoNumber());
            advShipNoticeInfo.setTotalQuantity(noticeInfo.getTotalQuantity());
            advShipNoticeInfo.setLotNumber(noticeInfo.getLotNumber());
            advShipNoticeInfo.setClient(clientRepo.findById(noticeInfo.getCreatedBy()).get());
            advShipNoticeInfo.setClient(clientRepo.findById(noticeInfo.getCreatedBy()).get());



        List<AsnUnit> asnUnitsList = new ArrayList<>();
        List<WareHouseReceivedItems> warehouseReceivedItemsList = new ArrayList<>();
        for (NoticeUnitDto noticeUnit : noticeInfo.getAsnUnitList()) {
            AsnUnit unit = new AsnUnit(noticeUnit.getQuantity(), noticeUnit.getReceivedQuantity(), noticeUnit.getLocation(), productRepo.findById(noticeUnit.getProductId()).get());
            asnUnitsList.add(unit);

            WareHouseReceivedItems warehouseReceivedItems = new WareHouseReceivedItems();

            warehouseReceivedItems.setWarehouseId(noticeInfo.getWarehouseId());
            warehouseReceivedItems.setClientId(unit.getProduct().getClientId());
            warehouseReceivedItems.setQuantity(noticeUnit.getReceivedQuantity());
            warehouseReceivedItems.setUserId(noticeInfo.getCreatedBy());
            warehouseReceivedItems.setProductId(noticeUnit.getProductId());
            warehouseReceivedItems.setCreatedOn(LocalDateTime.now());
            warehouseReceivedItems.setLotNumber(noticeInfo.getLotNumber());
            warehouseReceivedItems.setInventoryStage(InventoryStage.ON_HAND);
            warehouseReceivedItems.setReceiveStatus(ReceiveStatus.RECEIVED);

           wareHouseReceivedRepo.save(warehouseReceivedItems);
        }
        advShipNoticeInfo.setAsnUnitsList(asnUnitsList);  // set the saved ASNNotice with its units
        AsnInfo savedNotice = asnInfoRepo.save(advShipNoticeInfo);  // Save ASNNotice to return

        return savedNotice;
    }
}
