package com.example.demo.coffeShop.Service.Impl.JwtImpl;

import com.example.demo.coffeShop.Entity.JWT.Role;
import com.example.demo.coffeShop.Repositori.JWT.RoleRepositori;
import com.example.demo.coffeShop.Service.JwtService.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    public final RoleRepositori roleRepositori;

    @Override
    public Role getOrSave(Role role) {
        Optional<Role> optionalRole = roleRepositori.findByRole(role.getRole());

        if (!optionalRole.isEmpty()){
            return  optionalRole.get();
        }
        return roleRepositori.save(role);
    }
}
