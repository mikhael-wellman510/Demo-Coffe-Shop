package com.example.demo.coffeShop.DTO.Request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class EmailRequest {

    private String email;

    @Override
    public String toString() {
        return "EmailRequest{" +
                "email='" + email + '\'' +
                '}';
    }
}
