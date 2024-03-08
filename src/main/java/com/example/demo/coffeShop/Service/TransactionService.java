package com.example.demo.coffeShop.Service;

import com.example.demo.coffeShop.DTO.Request.TransactionDetailRequest;
import com.example.demo.coffeShop.DTO.Request.TransactionRequest;
import com.example.demo.coffeShop.DTO.Response.TransactionResponse;

public interface TransactionService {
    TransactionResponse createTransaction(TransactionRequest transactionRequest);

}
