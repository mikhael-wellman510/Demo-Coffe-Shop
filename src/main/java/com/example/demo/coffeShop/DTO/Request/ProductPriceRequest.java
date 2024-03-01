package com.example.demo.coffeShop.DTO.Request;


import com.example.demo.coffeShop.Constant.Kategori;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProductPriceRequest {
    private String id;
    private MultipartFile images;
    //todo -> M_product
    private String productName;
    private Kategori kategori;
    //todo ->M_product_prices
    private Integer price;
    private Integer stock;
    // Todo -> Get storeId
    private String storeId;

    @Override
    public String toString() {
        return "ProductPriceRequest{" +
                "id='" + id + '\'' +
                ", images=" + images +
                ", productName='" + productName + '\'' +
                ", kategori=" + kategori +
                ", price=" + price +
                ", stock=" + stock +
                ", storeId='" + storeId + '\'' +
                '}';
    }
}
