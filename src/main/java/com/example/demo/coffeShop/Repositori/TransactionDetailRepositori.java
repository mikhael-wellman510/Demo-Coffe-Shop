package com.example.demo.coffeShop.Repositori;

import com.example.demo.coffeShop.Entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDetailRepositori extends JpaRepository<TransactionDetail,String> {
}
