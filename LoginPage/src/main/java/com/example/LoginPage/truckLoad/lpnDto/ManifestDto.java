package com.example.LoginPage.truckLoad.lpnDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManifestDto {
    private String manifestNo;
    private List<String> lpnNo;
    private List<String> orderId;

}
