package com.example.demo.coffeShop.DTO.Request;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TransactionRequest {
    private String customerId;
    private List<TransactionDetailRequest> transactionDetail;

    @Override
    public String toString() {
        return "TransactionRequest{" +
                "customerId='" + customerId + '\'' +
                ", transactionDetail=" + transactionDetail +
                '}';
    }
}
