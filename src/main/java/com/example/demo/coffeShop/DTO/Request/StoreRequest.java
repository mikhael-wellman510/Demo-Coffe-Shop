package com.example.demo.coffeShop.DTO.Request;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class StoreRequest {

    private String id;
    private String storeName;
    private String address;
    private String mobilePhone;
}
