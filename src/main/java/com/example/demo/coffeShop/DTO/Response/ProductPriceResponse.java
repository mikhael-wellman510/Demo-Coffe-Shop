package com.example.demo.coffeShop.DTO.Response;

import com.example.demo.coffeShop.Constant.Kategori;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductPriceResponse {
    private String id;
    private Integer price;
    private Integer stock;
    private ProductResponse productResponse;
    private StoreResponse storeResponse;

    @Override
    public String toString() {
        return "ProductPriceResponse{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", productResponse=" + productResponse +
                ", storeResponse=" + storeResponse +
                '}';
    }
}
