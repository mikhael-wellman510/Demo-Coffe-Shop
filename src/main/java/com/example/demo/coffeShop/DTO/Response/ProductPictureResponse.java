package com.example.demo.coffeShop.DTO.Response;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductPictureResponse {
    private String id;
    private String imageUrl;
    private LocalDateTime updatedAt;
    private LocalDateTime uploadAt;

    @Override
    public String toString() {
        return "ProductPictureResponse{" +
                "id='" + id + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", updatedAt=" + updatedAt +
                ", uploadAt=" + uploadAt +
                '}';
    }
}
