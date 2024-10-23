package com.example.LoginPage.ship.impl;

import com.example.LoginPage.oms.entity.CilOrderInfo;
import com.example.LoginPage.oms.entity.FepOrdersInfo;
import com.example.LoginPage.oms.enumm.OrderStatus;
import com.example.LoginPage.oms.repo.CilOrderInfoRepo;
import com.example.LoginPage.oms.repo.FepOrderInfoRepo;
import com.example.LoginPage.pickAndPack.entity.AssignedPicker;
import com.example.LoginPage.pickAndPack.repo.AssignedPickerRepo;
import com.example.LoginPage.ship.dto.OrderResponseDto;
import com.example.LoginPage.ship.dto.ScanBoxLabelDto;
import com.example.LoginPage.ship.dto.shipmentRequestDto.*;
import com.example.LoginPage.ship.dto.shipmentRequestDto.LabelShipment;
import com.example.LoginPage.warehouse.entity.Warehouse;
import com.example.LoginPage.warehouse.repo.WarehouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShippingImpl {

    @Autowired
    CilOrderInfoRepo cilOrderInfoRepo;
    @Autowired
    WarehouseRepo warehouseRepo;
    @Autowired
    AssignedPickerRepo assignedPickerRepo;
    @Autowired
    FepOrderInfoRepo fepOrderInfoRepo;

    public ResponseEntity<?> generateShippingLabel(String boxId) {
        Optional<AssignedPicker> assignPicker = assignedPickerRepo.findByBoxId(boxId);
        AssignedPicker assignedPicker = assignPicker.get();

        if (assignPicker.isEmpty()) {
            return new ResponseEntity<>("This boxId is not Found", HttpStatus.OK);
        }

        String orderId = assignedPicker.getOrderId();
        Optional<CilOrderInfo> cilOrderInfo = cilOrderInfoRepo.findByOrderId(orderId);
        if (cilOrderInfo.isPresent()) {
            CilOrderInfo orderInfo = cilOrderInfo.get();
            Long warehouseId = orderInfo.getWarehouseId();
            Warehouse warehouse = warehouseRepo.findById(warehouseId).orElse(null);

            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setOrderNumber(orderInfo.getOrderId());
            orderResponseDto.setCarrier(orderInfo.getCarrierType());
            orderResponseDto.setShipToAddress1(orderInfo.getShipToAddress1());
            orderResponseDto.setShipToAddress2(orderInfo.getShipToAddress2());
            orderResponseDto.setShipToCity(orderInfo.getShipToCity());
            orderResponseDto.setShipToState(orderInfo.getShipToState());
            orderResponseDto.setShipToCountry(orderInfo.getShipToCountry());
            orderResponseDto.setShipToName(orderInfo.getShipToName());
            orderResponseDto.setShipToPostalCode(orderInfo.getShipToPostalCode());
            orderResponseDto.setShipToIsResidential(orderInfo.getShipToIsResidential());


            orderResponseDto.setShipFromName(warehouse.getShipFromName());
            orderResponseDto.setShipFromAddress1(warehouse.getShipFromAddress1());
            orderResponseDto.setShipFromAddress2(warehouse.getShipFromAddress2());
            orderResponseDto.setShipFromCountry(warehouse.getShipFromCountry());
            orderResponseDto.setShipFromCity(warehouse.getShipFromCity());
            orderResponseDto.setShipFromState(warehouse.getShipFromState());
            orderResponseDto.setShipFromPostalCode(warehouse.getShipFromPostalCode());
            orderResponseDto.setShipFromPostalCode(warehouse.getShipFromPostalCode());


            return new ResponseEntity<>(orderResponseDto,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Sorry No Data Found", HttpStatus.OK);
        }
    }


    public ResponseEntity<?> saveDimensionAndWeight(ScanBoxLabelDto scanBoxLabelDto){

        Optional<AssignedPicker> optionalAssignedPicker = assignedPickerRepo.findByOrderId(scanBoxLabelDto.getOrderId());
        if(optionalAssignedPicker.isPresent()){
            AssignedPicker assignedPicker = optionalAssignedPicker.get();

            assignedPicker.setBoxLabel(scanBoxLabelDto.getBoxLabel());
            assignedPicker.setBoxWeight(scanBoxLabelDto.getBoxWeight());
            assignedPicker.setBoxHeight(scanBoxLabelDto.getBoxDimension().getHeight());
            assignedPicker.setBoxWidth(scanBoxLabelDto.getBoxDimension().getWidth());
            assignedPicker.setBoxLenght(scanBoxLabelDto.getBoxDimension().getLength());


            // change status in fep layer

            Optional<FepOrdersInfo> optionalFepOrdersInfo = fepOrderInfoRepo.findByOrderId(scanBoxLabelDto.getOrderId());
            FepOrdersInfo fepOrdersInfo = optionalFepOrdersInfo.get();
            fepOrdersInfo.setStatus(OrderStatus.READYTOSHIP);
            fepOrderInfoRepo.save(fepOrdersInfo);

            assignedPickerRepo.save(assignedPicker);



//            return new ResponseEntity<>("Box Dimensions Updated",HttpStatus.OK);
            return getShippingLabel(assignedPicker.getBoxId());
        }

     return new ResponseEntity<>("Order Id not found",HttpStatus.NOT_FOUND);
    }




    public ResponseEntity<?> getShippingLabel(String boxId) {
      try{
          Shipment shipment = new Shipment();
          RestTemplate restTemplate = new RestTemplate();
          URI uri = new URI("https://apisandbox.tusklogistics.com/v1/labels");

          OrderResponseDto orderResponseDto = (OrderResponseDto)generateShippingLabel(boxId).getBody();

          // From
          AddressFrom addressFrom = new AddressFrom();
          addressFrom.setStreet1(orderResponseDto.getShipFromAddress1());
          addressFrom.setStreet2(orderResponseDto.getShipFromAddress2());
          addressFrom.setName(orderResponseDto.getShipFromName());
          addressFrom.setCountry(orderResponseDto.getShipFromCountry());
          addressFrom.setCity(orderResponseDto.getShipFromCity());
          addressFrom.setState(orderResponseDto.getShipFromState());
          addressFrom.setPostalCode(orderResponseDto.getShipFromPostalCode());
          addressFrom.setIsResidential(orderResponseDto.getShipFromIsResidential());
          shipment.setAddressFrom(addressFrom);

          System.out.println("Address From:");
          System.out.println("Name: " + addressFrom.getName());
          System.out.println("Street1: " + addressFrom.getStreet1());
          System.out.println("Country :" + addressFrom.getCountry());
          System.out.println("City: " + addressFrom.getCity());
          System.out.println("State: " + addressFrom.getState());
          System.out.println("Postal Code: " + addressFrom.getPostalCode());



          // To
          AddressTo addressTo = new AddressTo();
          addressTo.setStreet1(orderResponseDto.getShipToAddress1());
          addressTo.setStreet2(orderResponseDto.getShipToAddress2());
          addressTo.setName(orderResponseDto.getShipToName());
          addressTo.setCountry(orderResponseDto.getShipToCountry());
          addressTo.setCity(orderResponseDto.getShipToCity());
          addressTo.setState(orderResponseDto.getShipToState());
          addressTo.setPostalCode(orderResponseDto.getShipToPostalCode());
          addressTo.setIsResidential(orderResponseDto.getShipToIsResidential());
          Optional<AssignedPicker> assignedPicker = assignedPickerRepo.findByOrderId(orderResponseDto.getOrderNumber());
          shipment.setAddressTo(addressTo);

          System.out.println("Address To:");
          System.out.println("Name: " + addressTo.getName());
          System.out.println("Street1: " + addressTo.getStreet1());
          System.out.println("Country :" + addressTo.getCountry());
          System.out.println("City: " + addressTo.getCity());
          System.out.println("State: " + addressTo.getState());
          System.out.println("Postal Code: " + addressTo.getPostalCode());

          //Parcel
          List<Parcel> parcelList = new ArrayList<>();
          Parcel parcel = new Parcel();
          Weight weight = new Weight();
          weight.setUnit("Ounce");
          weight.setValue(assignedPicker.get().getBoxWeight());
          System.out.println("Weight : " + weight.getValue());

          Dimensions dimensions = new Dimensions();
          dimensions.setWidth(assignedPicker.get().getBoxWidth());
          dimensions.setHeight(assignedPicker.get().getBoxHeight());
          dimensions.setLength(assignedPicker.get().getBoxLenght());

          parcel.setDimensions(dimensions);
          parcel.setWeight(weight);
          parcelList.add(parcel);
          shipment.setParcels(parcelList);

          shipment.setExternalReference(orderResponseDto.getOrderNumber());
          LabelShipment labelShipment = new LabelShipment();
          labelShipment.setShipment(shipment);


          HttpHeaders httpHeaders = new HttpHeaders();
          httpHeaders.set("Content-Type", "application/json");
          httpHeaders.set("x-api-key", "7Du28nKx66p6PloG9iGz9Vbg9PZINZCuIUXdahH5");


          HttpEntity<LabelShipment> httpEntity = new HttpEntity<>(labelShipment, httpHeaders);
          ResponseEntity<ReturnData> result = restTemplate.postForEntity(uri, httpEntity, ReturnData.class);
          ReturnData returnData=result.getBody();
          return new ResponseEntity<>(returnData,HttpStatus.OK);
      }catch (Exception e){
          return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
      }

    }
}

































































//    public OrderResponseDto generateShippingLabel(String orderId) {
//        Optional<CilOrderInfo> cilOrderInfo = cilOrderInfoRepo.findByOrderId(orderId);
//        if (cilOrderInfo.isPresent()) {
//            CilOrderInfo orderInfo = cilOrderInfo.get();
//            Long warehouseId = orderInfo.getWarehouseId();
//            Warehouse warehouse = warehouseRepo.findById(warehouseId).orElse(null);
//
//            if (warehouse != null) {
//                return new OrderResponseDto(
//                        orderInfo.getOrderId(),
//                        orderInfo.getCarrierType(),
//                        orderInfo.getServiceType(),
//                        orderInfo.getShipToName(),
//                        orderInfo.getShipToAddress1(),
//                        orderInfo.getShipToAddress2(),
//                        orderInfo.getShipToCity(),
//                        orderInfo.getShipToState(),
//                        orderInfo.getShipToCountry(),
//                        orderInfo.getShipToPostalCode(),
//                        orderInfo.getShipToIsResidential(),
//                        warehouse.getWarehouseName(),
//                        warehouse.getShipFromAddress1(),
//                        warehouse.getShipFromAddress2(),
//                        warehouse.getShipFromCity(),
//                        warehouse.getShipFromState(),
//                        warehouse.getShipFromCountry(),
//                        warehouse.getShipFromPostalCode(),
//                        warehouse.getShipFromIsResidential()
//                );
//            }
//        }
//        return null;
//    }