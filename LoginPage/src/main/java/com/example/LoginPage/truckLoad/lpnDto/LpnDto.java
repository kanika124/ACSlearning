package com.example.LoginPage.truckLoad.lpnDto;

import com.example.LoginPage.pickAndPack.dto.OrderContainerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LpnDto {
    private String lpnNo;
    private List<String> orderId;
}
