package com.example.demo.coffeShop.Repositori;

import com.example.demo.coffeShop.Entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailRepositori extends JpaRepository<TransactionDetail,String> {
}
