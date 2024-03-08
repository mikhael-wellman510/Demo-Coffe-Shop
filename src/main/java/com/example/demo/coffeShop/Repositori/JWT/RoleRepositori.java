package com.example.demo.coffeShop.Repositori.JWT;

import com.example.demo.coffeShop.Constant.JWT.Erole;
import com.example.demo.coffeShop.Entity.JWT.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepositori extends JpaRepository<Role, String> {
    Optional<Role> findByRole(Erole name);
}
