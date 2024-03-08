package com.example.demo.coffeShop.Repositori.JWT;

import com.example.demo.coffeShop.Entity.JWT.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepositori extends JpaRepository<Customer , String> {
    Optional<Customer> findByUserCredentialId(String userCredentialId);
}
