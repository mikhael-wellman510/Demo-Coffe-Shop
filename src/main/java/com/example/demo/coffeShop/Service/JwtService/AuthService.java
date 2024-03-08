package com.example.demo.coffeShop.Service.JwtService;

import com.example.demo.coffeShop.DTO.Request.JWT.AuthRequest;
import com.example.demo.coffeShop.DTO.Response.JWT.LoginResponse;
import com.example.demo.coffeShop.DTO.Response.JWT.RegisterResponse;

public interface AuthService {
    RegisterResponse registerCustomer(AuthRequest authRequest);
    LoginResponse login(AuthRequest authRequest);
    RegisterResponse registerAdmin(AuthRequest authRequest);
}
