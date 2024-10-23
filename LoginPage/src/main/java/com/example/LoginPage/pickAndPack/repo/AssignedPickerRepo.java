package com.example.LoginPage.pickAndPack.repo;

import com.example.LoginPage.pickAndPack.entity.AssignedPicker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignedPickerRepo extends JpaRepository<AssignedPicker,Long> {
//    boolean existByContainerId(String containerId);

    List<AssignedPicker> findByContainerId(String containerId);

    Optional<AssignedPicker> findByBoxId(String boxId);

    Optional<AssignedPicker> findByOrderId(String orderId);
}
