package com.example.demo.coffeShop.SecurityJwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.coffeShop.Entity.JWT.AppUser;
import com.example.demo.coffeShop.Entity.JWT.Customer;
import com.example.demo.coffeShop.Service.JwtService.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component

public class JwtUtil {

    @Value("${app.demo.coffeShop.jwt.jwt-secret}")
    private String jwtSecret;

    @Value("${app.demo.coffeShop.jwt.app-name}")
    private String appName;

    @Value("${app.demo.coffeShop.jwt.jwtExpirationInSecond}")
    private long jwtExpirationInSecond;

    private final CustomerService customerService;
    public JwtUtil(CustomerService customerService) {
         this.customerService = customerService;
    }

    public String generateToken(AppUser appUser){
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));

            // Todo -> Untuk mendapatkan Customer id dan ketika di decoded dapat
//            Customer customer = customerService.findByUserCredentialId(appUser.getId());
//
//            String customerId = customer.getId();

            String token = JWT.create()
                    .withIssuer(appName)
                    .withSubject(appUser.getId())
                    .withExpiresAt(Instant.now().plusSeconds(jwtExpirationInSecond))
                    .withIssuedAt(Instant.now())
                    .withClaim("role" ,appUser.getRole().name())
                    .withClaim("username", appUser.getUsername())
//                    .withClaim("customerId" , customerId)
                    .sign(algorithm);

            return token;

        }catch (JWTCreationException e){
            throw new RuntimeException();
        }
    }

    public boolean verifyJwtToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);

            return decodedJWT.getIssuer().equals(appName);

        }catch (JWTVerificationException e){
            System.out.println("cek");
            throw new RuntimeException();
        }
    }

    public Map<String, String> getUsherInfoByToken(String token){

        try {

            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            Map<String , String> userInfo = new HashMap<>();
            userInfo.put("userId" , decodedJWT.getSubject());
            userInfo.put("role" , decodedJWT.getClaim("role").asString());
            userInfo.put("username" , decodedJWT.getClaim("username").asString());


            return userInfo;

        }catch (JWTVerificationException e){
            throw new RuntimeException();
        }

    }

    public String getInfoToken(AppUser appUser){
        try {

            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
            String namaUser = JWT.create()
                    .withIssuer(appName)
                    .withSubject(appUser.getId())
                    .withExpiresAt(Instant.now().plusSeconds(jwtExpirationInSecond))
                    .withIssuedAt(Instant.now())
                    .withClaim("role" , appUser.getRole().name())
                    .withClaim("username" , appUser.getUsername())
                    .sign(algorithm);
            return namaUser;

        }catch (JWTCreationException e){
            throw new RuntimeException();
        }
    }

}
