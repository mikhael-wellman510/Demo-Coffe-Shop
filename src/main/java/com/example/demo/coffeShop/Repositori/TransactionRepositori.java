package com.example.demo.coffeShop.Repositori;

import com.example.demo.coffeShop.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepositori extends JpaRepository<Transaction, String> {
}
