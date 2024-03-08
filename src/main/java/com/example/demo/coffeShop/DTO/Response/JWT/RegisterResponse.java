package com.example.demo.coffeShop.DTO.Response.JWT;

import com.example.demo.coffeShop.Entity.JWT.UserCredential;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RegisterResponse {
    private String username;
    private String role;
    private UserCredential userCredential;
}
