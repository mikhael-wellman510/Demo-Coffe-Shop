package com.example.demo.coffeShop.DTO.Response;


import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class StoreResponse {
    private String id;
    private String storeName;
    private String address;
    private String mobilePhone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isActive;

}
