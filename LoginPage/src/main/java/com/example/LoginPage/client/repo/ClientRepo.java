package com.example.LoginPage.client.repo;

import com.example.LoginPage.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {

    @Query(value = "select product_id from client where id =:cId", nativeQuery = true)
    List<Long> findProductIdsByClientId(Long cId);
}
