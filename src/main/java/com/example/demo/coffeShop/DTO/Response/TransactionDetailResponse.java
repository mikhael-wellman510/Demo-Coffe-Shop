package com.example.demo.coffeShop.DTO.Response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TransactionDetailResponse {
    private String id;
    private Integer qty;
    private String productName;
    private Integer price;

}
