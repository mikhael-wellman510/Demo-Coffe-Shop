package com.example.demo.coffeShop.Service.Impl.JwtImpl;

import com.example.demo.coffeShop.Entity.JWT.AppUser;
import com.example.demo.coffeShop.Entity.JWT.UserCredential;
import com.example.demo.coffeShop.Repositori.JWT.UserCredentialRepositori;
import com.example.demo.coffeShop.Service.JwtService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserCredentialRepositori userCredentialRepositori;

    @Override
    public AppUser loadUserByUserId(String id) {

        UserCredential userCredential = userCredentialRepositori.findById(id).orElseThrow(()-> new UsernameNotFoundException("Invalid Credential"));

        return AppUser.builder()
                .id(userCredential.getId())
                .username(userCredential.getUsername())
                .password(userCredential.getPassword())
                .role(userCredential.getRole().getRole())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserCredential userCredential = userCredentialRepositori.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Invalid Credentials"));

        return AppUser.builder()
                .id(userCredential.getId())
                .username(userCredential.getUsername())
                .password(userCredential.getPassword())
                .role(userCredential.getRole().getRole())
                .build();
    }
}
