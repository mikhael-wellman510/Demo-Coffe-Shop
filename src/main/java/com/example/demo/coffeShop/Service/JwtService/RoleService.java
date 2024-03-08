package com.example.demo.coffeShop.Service.JwtService;

import com.example.demo.coffeShop.Entity.JWT.Role;

public interface RoleService {
    Role getOrSave(Role role);
}
