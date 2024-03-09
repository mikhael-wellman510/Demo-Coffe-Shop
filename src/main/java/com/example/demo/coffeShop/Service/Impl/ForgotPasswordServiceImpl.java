package com.example.demo.coffeShop.Service.Impl;

import com.example.demo.coffeShop.Entity.JWT.UserCredential;
import com.example.demo.coffeShop.Repositori.JWT.UserCredentialRepositori;
import com.example.demo.coffeShop.SecurityJwt.JwtUtil;
import com.example.demo.coffeShop.Service.ForgotPasswordService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    private final UserCredentialRepositori userCredentialRepositori;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSetPasswordEmail(String email) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Set Password");


        // Todo -> Di jadikan Token dan expired 60 detik
        String token = jwtUtil.generateTokenForgotPassword(email);

        // Todo -> Masukan Page dari React Js
        mimeMessageHelper.setText(""" 
                <div> <a href="http://localhost:8000/api/password/set-password?email=%s" target= "_blank"> click link to set password </a>  </div>
                """.formatted(token),true);
        javaMailSender.send(mimeMessage);
    }

    @Override
    public String forgotPassword(String email) throws MessagingException {

        // Todo -> Find Username / Email
        UserCredential userCredential = userCredentialRepositori.findByUsername(email).orElse(null);

      if (userCredential != null){
          sendSetPasswordEmail(email);
          System.out.println("cek");
          return "Succes";
      }

      return null;

    }

    @Override
    public String setPassword(String token, String newPassword) {


        // Todo -> Ada expired Token selama 1 menit
        try {
            Boolean result = jwtUtil.verifyJwtToken(token);

            if (result){
                String email = jwtUtil.getInfoTokenForgotPassword(token);

                UserCredential userCredential = userCredentialRepositori.findByUsername(email).orElse(null);

                userCredential.setPassword(passwordEncoder.encode(newPassword));
                userCredentialRepositori.save(userCredential);
                return "Success";
            }
        }catch (Exception e){
            System.out.println(e);

        }
        return null;
    }
}
