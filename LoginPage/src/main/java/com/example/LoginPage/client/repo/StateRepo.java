package com.example.LoginPage.client.repo;

import com.example.LoginPage.address.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepo extends JpaRepository<State, Long> {
}
