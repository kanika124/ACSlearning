package com.example.LoginPage.pickAndPack.entity;

import com.example.LoginPage.pickAndPack.enumm.PickerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignedPicker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "assignedBy")
    private String pickerName;
    private LocalDate createdOn;
    @Column(name = "assignedOn")
    private LocalDate updatedOn;
    private String orderId;
    @Enumerated(EnumType.STRING)
    private PickerStatus pickerStatus;
    private String containerId;
    private Long productId;
    private Integer quantity;

    private String boxId;
    private String boxLabel;
    private Double boxWidth;
    private Double boxHeight;
    private Double boxLenght;
    private Double boxWeight;
}
