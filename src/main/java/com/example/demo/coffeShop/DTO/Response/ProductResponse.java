package com.example.demo.coffeShop.DTO.Response;

import com.example.demo.coffeShop.Constant.Kategori;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProductResponse {
    private String id ;
    private LocalDateTime createdAt;
    private Kategori kategori;
    private String productName;
    private LocalDateTime updateAt;
    private ProductPictureResponse productPictureResponse;

    @Override
    public String toString() {
        return "ProductResponse{" +
                "id='" + id + '\'' +
                ", createdAt=" + createdAt +
                ", kategori=" + kategori +
                ", productName='" + productName + '\'' +
                ", updateAt=" + updateAt +
                ", productPictureResponse=" + productPictureResponse +
                '}';
    }
}
