package com.example.demo.coffeShop.Service.Impl;

import com.example.demo.coffeShop.Entity.Transaction;
import com.example.demo.coffeShop.Repositori.TransactionRepositori;
import com.example.demo.coffeShop.Service.TrxService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrxServiceImpl implements TrxService {

    private final TransactionRepositori transactionRepositori;
    @Override
    public Transaction addTrx(Transaction transaction) {
        return transactionRepositori.saveAndFlush(transaction);
    }
}
