package com.example.LoginPage.client.repo;

import com.example.LoginPage.address.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepo extends JpaRepository<City, Long> {
    Optional<City> findById(Long cityId);
}
