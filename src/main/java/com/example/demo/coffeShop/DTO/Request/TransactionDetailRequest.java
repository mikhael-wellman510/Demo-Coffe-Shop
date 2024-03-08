package com.example.demo.coffeShop.DTO.Request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TransactionDetailRequest {

    private String productPriceId   ;
    private Integer qty;

    @Override
    public String toString() {
        return "TransactionDetailRequest{" +
                "productPriceId='" + productPriceId + '\'' +
                ", qty=" + qty +
                '}';
    }
}
