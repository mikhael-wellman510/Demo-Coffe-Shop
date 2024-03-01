package com.example.demo.coffeShop.Repositori;

import com.example.demo.coffeShop.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositori extends JpaRepository<Product , String> {
}
