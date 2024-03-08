package com.example.demo.coffeShop.DTO.Response.JWT;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LoginResponse {
    private String token;
    private String role;
    private String name;
}
