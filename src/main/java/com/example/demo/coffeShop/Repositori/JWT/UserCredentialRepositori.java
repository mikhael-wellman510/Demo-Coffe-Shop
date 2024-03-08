package com.example.demo.coffeShop.Repositori.JWT;

import com.example.demo.coffeShop.Entity.JWT.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialRepositori extends JpaRepository<UserCredential , String> {
Optional<UserCredential> findByUsername(String username);

}
