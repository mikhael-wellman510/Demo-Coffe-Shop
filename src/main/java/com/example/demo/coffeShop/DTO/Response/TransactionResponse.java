package com.example.demo.coffeShop.DTO.Response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TransactionResponse {
    private String transactionId;
    private LocalDateTime transactionDate;
    private CustomerResponse customerResponse;
    private List<TransactionDetailResponse> transactionDetailResponses;
    private Integer totalPrice;
}
