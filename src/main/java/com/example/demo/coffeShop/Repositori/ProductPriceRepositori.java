package com.example.demo.coffeShop.Repositori;

import com.example.demo.coffeShop.Entity.ProductPrices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPriceRepositori extends JpaRepository<ProductPrices , String> {
    List<ProductPrices> findAllByIsActiveTrue();
    Page<ProductPrices> findAllByIsActiveTrue(Pageable pageable);
}
