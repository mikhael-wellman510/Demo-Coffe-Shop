package com.example.demo.coffeShop.Repositori;

import com.example.demo.coffeShop.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepositori extends JpaRepository<Customer , String> {
}
