package com.example.demo.coffeShop.DTO.Request.JWT;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AuthRequest {
    // Todo - > Gabungan dari user credential dan customer
    private String id;
    private String nama;
    private String noHp;
    private String email;
    private String address;

    // Todo - > userCredential
    private String username;
    private String password;


    @Override
    public String toString() {
        return "AuthRequest{" +
                "id='" + id + '\'' +
                ", nama='" + nama + '\'' +
                ", noHp='" + noHp + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
