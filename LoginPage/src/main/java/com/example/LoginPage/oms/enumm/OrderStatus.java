package com.example.LoginPage.oms.enumm;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum OrderStatus {


        INITIATED,CREATED,ASSIGNED,BACKORDER,PICKED,PACKED,READYTOSHIP,SHIPPED,RECEIVED;
//        INITIATED(0),
//        CREATED(1),
//        ASSIGNED(15),
//        PICKED(3),
//        PACKED(4),
//        RECEIVED(5),
//        BACKORDER(18);

//        private final int code;
//
//        // Constructor
//        OrderStatus(int code,String message) {
//            this.code = code;
//        }
//
//        // Getter for code
//        public int getCode() {
//            return code;
//        }
//
//        // Method to get enum from code
//
//    public static OrderStatus fromCode(int code, String message) {
//            for (OrderStatus status : OrderStatus.values()) {
//                if (status.getCode() == code) {
//                    return status;
//                }
//            }
//            throw new IllegalArgumentException("Invalid code: " + code);
//        }
}
