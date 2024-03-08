package com.example.demo.coffeShop.Controller;

import com.example.demo.coffeShop.Constant.AppPath;
import com.example.demo.coffeShop.DTO.Request.JWT.AuthRequest;
import com.example.demo.coffeShop.DTO.Response.CommonResponse;
import com.example.demo.coffeShop.DTO.Response.JWT.LoginResponse;
import com.example.demo.coffeShop.DTO.Response.JWT.RegisterResponse;
import com.example.demo.coffeShop.Service.JwtService.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.AUTH)
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/registerCust")
    public ResponseEntity<?> registerCustomer(@RequestBody AuthRequest authRequest){

        RegisterResponse registerResponse = authService.registerCustomer(authRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<RegisterResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Succesfull Register")
                        .data(registerResponse)
                        .build()
                );

    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        LoginResponse loginResponse = authService.login(authRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<LoginResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Succesfull login")
                        .data(loginResponse)
                        .build()
                );
    }

}
