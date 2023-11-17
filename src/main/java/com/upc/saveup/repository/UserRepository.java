package com.upc.saveup.repository;

import com.upc.saveup.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    //User findByPhoneNumber(String phoneNumber);
    User findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
    //boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
   // boolean existsByEmailAndPhoneNumber(String email, String phoneNumber);
}