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
    private String productName;
    private Kategori kategori;
    private String imageUrl;
    private StoreResponse storeResponse;
}
