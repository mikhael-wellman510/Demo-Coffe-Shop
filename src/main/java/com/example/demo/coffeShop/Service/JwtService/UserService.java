package com.example.demo.coffeShop.Service.JwtService;

import com.example.demo.coffeShop.Entity.JWT.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser loadUserByUserId(String id);
}
