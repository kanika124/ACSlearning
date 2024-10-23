package com.example.LoginPage.pickAndPack.dto;

import com.example.LoginPage.pickAndPack.enumm.BoxType;
import lombok.Data;

@Data
public class BoxDto {
    private Integer quantity;
    private Long warehouseId;
    private BoxType boxType;

}






//    we will give the number of product to pick and you have to put the condition on that and allot a box to particular product. After alloting a box, make box status active to inactive in a database and return the list of same boxtype id of boxes