package com.example.demo.coffeShop.DTO.Response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CustomerResponse {
    private String id;
    private String nama;
    private String noHp;
    private String address;
    private String email;
}
