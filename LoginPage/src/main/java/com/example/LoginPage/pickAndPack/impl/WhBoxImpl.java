package com.example.LoginPage.pickAndPack.impl;

import com.example.LoginPage.oms.entity.FepOrdersInfo;
import com.example.LoginPage.oms.entity.FepOrdersItems;
import com.example.LoginPage.oms.enumm.OrderStatus;
import com.example.LoginPage.oms.repo.FepOrderInfoRepo;
import com.example.LoginPage.pickAndPack.dto.AssignedPickerDto;
import com.example.LoginPage.pickAndPack.dto.BoxDto;
import com.example.LoginPage.pickAndPack.dto.OrderContainerDto;
import com.example.LoginPage.pickAndPack.dto.PickerUserDto;
import com.example.LoginPage.pickAndPack.entity.AssignedPicker;
import com.example.LoginPage.pickAndPack.entity.WhBoxLabel;
import com.example.LoginPage.pickAndPack.enumm.PickerStatus;
import com.example.LoginPage.pickAndPack.repo.AssignedPickerRepo;
import com.example.LoginPage.pickAndPack.repo.WhBoxRepo;
import com.example.LoginPage.product.enumm.Status;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.LocalDate;
import java.util.*;

@Service
public class WhBoxImpl {
    @Autowired
    WhBoxRepo whBoxRepo;
    @Autowired
    AssignedPickerRepo assignedPickerRepo;
    @Autowired
    FepOrderInfoRepo fepOrderInfoRepo;
//    @Autowired
//    PickerUserDto pickerUserDto;



    public List<Long> printBoxLabel(BoxDto boxDto){
        List<Long> boxIdList = new ArrayList<>();

        List<WhBoxLabel> availableBox = whBoxRepo.findByWarehouseIdAndBoxTypeAndStatus(boxDto.getWarehouseId(), boxDto.getBoxType(), Status.ACTIVE);


        if(availableBox.isEmpty() || boxDto.getQuantity()<=0){
            System.out.println("Quantity must be greater than Zero");
            System.out.println("No box available now");
        } else if (availableBox.size() < boxDto.getQuantity()) {
            System.out.println("Quantity Exceeded");

            //iterating over the given list
            //And at least providing the available boxes
            for (WhBoxLabel box : availableBox) {

                //adding the box id to result list
                boxIdList.add(box.getId());
                //setting up the box status to inactive
                box.setStatus(Status.INACTIVE);
                whBoxRepo.save(box);
            }
        }
        else{
            System.out.println("Allocating number of boxes");

            //iterating over the given list till the required no of boxes allotted
            for(int i =0; i<boxDto.getQuantity(); i++){

                WhBoxLabel boxLabel = availableBox.get(i);
                //adding box id to result list
                boxIdList.add(boxLabel.getId());
                //setting up the box status to inactive
                boxLabel.setStatus(Status.INACTIVE);

                whBoxRepo.save(boxLabel);
            }
        }
        return boxIdList;
    }


    public List<Long> getProductIdsByOrderId(String orderId) {
        Optional<FepOrdersInfo> orderInfoOpt = fepOrderInfoRepo.findByOrderId(orderId);

        if (orderInfoOpt.isEmpty() || orderInfoOpt.get().getOrderItemsList() == null) {
            return List.of();
        }

        List<FepOrdersItems> orderItemsList = orderInfoOpt.get().getOrderItemsList();
        List<Long> productIds = new ArrayList<>();

        for (FepOrdersItems item : orderItemsList) {
            productIds.add(item.getProductId());
        }

        return productIds;
    }



    public ResponseEntity<?> assignPicker(AssignedPickerDto assignedPickerDto){
        if (assignedPickerDto.getOrderId() == null || assignedPickerDto.getPickerName() == null) {
            return new ResponseEntity<>("Order ID and Picker Name must not be null", HttpStatus.BAD_REQUEST);
        }

        // Verifying oderId is present or not in db

        Optional<FepOrdersInfo> optionalFepOrdersInfo = fepOrderInfoRepo.findByOrderId(assignedPickerDto.getOrderId());
        if (!optionalFepOrdersInfo.isPresent()) {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }

        AssignedPicker assignedPicker = new AssignedPicker();

        assignedPicker.setPickerName(assignedPickerDto.getPickerName());
        assignedPicker.setOrderId(assignedPickerDto.getOrderId());
        assignedPicker.setPickerStatus(PickerStatus.ASSIGNED);
        assignedPicker.setCreatedOn(LocalDate.now());
        assignedPicker.setUpdatedOn(LocalDate.now());
        assignedPicker.setBoxId(assignedPickerDto.getBoxId());
        assignedPickerRepo.save(assignedPicker);

        FepOrdersInfo fepOrdersInfo = optionalFepOrdersInfo.get();
        fepOrdersInfo.setStatus(OrderStatus.ASSIGNED);
        fepOrderInfoRepo.save(fepOrdersInfo);

        return new ResponseEntity<>("Picker Assigned Successfully",HttpStatus.CREATED);
    }


    public String getRandomContainerNumber() {
        Random random = new Random();
        int randomNum = random.nextInt();

        return "C" + randomNum;
    }

    public ResponseEntity<?> submitPickList(OrderContainerDto orderContainerDto){
        if (orderContainerDto.getOrderId() == null || orderContainerDto.getPickerName() == null) {
            return new ResponseEntity<>("Order ID and Picker Name must not be null", HttpStatus.BAD_REQUEST);
        }

        // Verifying oderId is present or not in db

        Optional<FepOrdersInfo> optionalFepOrdersInfo = fepOrderInfoRepo.findByOrderId(orderContainerDto.getOrderId());
        if (!optionalFepOrdersInfo.isPresent()) {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }

        //TODAY's ASSIGNMENT

        List<AssignedPicker> existedOrderList = assignedPickerRepo.findByContainerId(orderContainerDto.getOrderId());

        if(existedOrderList.size() != 0){
            String orderId = existedOrderList.get(0).getOrderId();
            return new ResponseEntity<>("In this container " + orderId + " is already picked, please use another container", HttpStatus.NOT_FOUND);
        }

        //Map<ProductId, Quantity>
        Map<Long, Long> fepItemMap = new HashMap<>();

        for(FepOrdersItems fepOrdersItem : optionalFepOrdersInfo.get().getOrderItemsList()) {
            fepItemMap.put(fepOrdersItem.getProductId(), fepOrdersItem.getProductQty());
        }


        for (Long productId : fepItemMap.keySet()){
            Long currentProductQty = 0L;
            for(PickerUserDto pickerUserDto : orderContainerDto.getPickerUserDtoList()) {
                if(pickerUserDto.getProductId() == productId){
                    currentProductQty += pickerUserDto.getQuantity();
                }
            }
            if (!fepItemMap.get(productId).equals(currentProductQty)){
                return new ResponseEntity<>("Product quantity mismatched : " + productId, HttpStatus.NOT_FOUND);
            }
        }


        for(PickerUserDto pickerUserDto : orderContainerDto.getPickerUserDtoList()) {
            AssignedPicker assignedPicker = new AssignedPicker();

            assignedPicker.setOrderId(orderContainerDto.getOrderId());
            assignedPicker.setPickerName(orderContainerDto.getPickerName());
            assignedPicker.setBoxId(orderContainerDto.getBoxId());
            assignedPicker.setCreatedOn(LocalDate.now());
            assignedPicker.setUpdatedOn(LocalDate.now());
            assignedPicker.setPickerStatus(PickerStatus.ASSIGNED);

            assignedPicker.setContainerId(pickerUserDto.getContainerId());
            assignedPicker.setProductId(pickerUserDto.getProductId());
            assignedPicker.setQuantity(pickerUserDto.getQuantity());

            FepOrdersInfo fepOrdersInfo = optionalFepOrdersInfo.get();
            fepOrdersInfo.setStatus(OrderStatus.PACKED);
            fepOrderInfoRepo.save(fepOrdersInfo);

            assignedPickerRepo.save(assignedPicker);
        }
        return new ResponseEntity<>("Assigned",HttpStatus.CREATED);
    }

    public ResponseEntity<?> scanContainer(String containerId){

        Optional<FepOrdersInfo> ordersInfo = fepOrderInfoRepo.findByOrderId(containerId);
        if(ordersInfo.isPresent()){
            FepOrdersInfo fepOrdersInfo = ordersInfo.get();
            List<OrderContainerDto> orderContainerDtos = new ArrayList<>();
//            for(FepOrdersItems item : fepOrdersInfo.getOrderItemsList()){
//                orderContainerDtos.add(new OrderContainerDto(fepOrdersInfo.getOrderId(),item.getProductId(),item.getProductQty()));
//            }
        }
        return null;
    }

}