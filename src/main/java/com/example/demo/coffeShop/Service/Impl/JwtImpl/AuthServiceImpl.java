package com.example.demo.coffeShop.Service.Impl.JwtImpl;

import com.example.demo.coffeShop.Constant.JWT.Erole;
import com.example.demo.coffeShop.DTO.Request.JWT.AuthRequest;
import com.example.demo.coffeShop.DTO.Response.JWT.LoginResponse;
import com.example.demo.coffeShop.DTO.Response.JWT.RegisterResponse;
import com.example.demo.coffeShop.Entity.JWT.AppUser;
import com.example.demo.coffeShop.Entity.JWT.Customer;
import com.example.demo.coffeShop.Entity.JWT.Role;
import com.example.demo.coffeShop.Entity.JWT.UserCredential;
import com.example.demo.coffeShop.Repositori.JWT.UserCredentialRepositori;
import com.example.demo.coffeShop.SecurityJwt.JwtUtil;
import com.example.demo.coffeShop.Service.JwtService.AuthService;
import com.example.demo.coffeShop.Service.JwtService.CustomerService;
import com.example.demo.coffeShop.Service.JwtService.RoleService;
import com.example.demo.coffeShop.Util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.validation.Validator;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final CustomerService customerService ;
    private final ValidationUtil validationUtil;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UserCredentialRepositori userCredentialRepositori;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public RegisterResponse registerCustomer(AuthRequest authRequest) {
        System.out.println("hasil  : " + authRequest);

        try {
            validationUtil.validate(authRequest);

            // Todo -> Set Role

            Role role = Role.builder()
                    .role(Erole.ROLE_CUSTOMER)
                    .build();

            Role roleResult = roleService.getOrSave(role);

          // Todo -> Set User Credential
            UserCredential userCredential = UserCredential.builder()
                    .username(authRequest.getUsername().toLowerCase())
                    .password(passwordEncoder.encode(authRequest.getPassword()))
                    .role(roleResult)
                    .build();

            userCredentialRepositori.saveAndFlush(userCredential);

            // Todo -> Set Customer

            Customer customer = Customer.builder()
                    .nama(authRequest.getNama())
                    .noHP(authRequest.getNoHp())
                    .email(authRequest.getEmail())
                    .address(authRequest.getAddress())
                    .userCredential(userCredential)
                    .build();
            customerService.addCustomer(customer);

            return RegisterResponse.builder()
                    .username(userCredential.getUsername())
                    .role(userCredential.getRole().getRole().toString())
                    .userCredential(userCredential)
                    .build();

        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user already exist");
        }

    }

    @Override
    public LoginResponse login(AuthRequest authRequest) {

        validationUtil.validate(authRequest);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getUsername().toLowerCase(),
                authRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Authentication authentications = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = (AppUser) authentication.getPrincipal();

        String token = jwtUtil.generateToken(appUser);
        String namaUser = jwtUtil.getUsherInfoByToken(token).get("username");



        return LoginResponse.builder()
                .token(token)
                .role(appUser.getRole().name())
                .name(namaUser)
                .build();
    }

    @Override
    public RegisterResponse registerAdmin(AuthRequest authRequest) {
        return null;
    }
}
