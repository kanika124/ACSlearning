package com.example.LoginPage.user.repo;

import com.example.LoginPage.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    @Query(value = "select client_id from user where id =:userId", nativeQuery = true)
    List<Long> findClientIdByUserId(Long userId);

}
