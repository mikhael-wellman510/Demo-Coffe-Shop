package com.example.demo.coffeShop.Controller;


import com.example.demo.coffeShop.Constant.AppPath;
import com.example.demo.coffeShop.DTO.Request.EmailRequest;
import com.example.demo.coffeShop.Service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.EMAIL)
public class SendEmailController {

    private final EmailService emailService;

    @PostMapping("/sendEmail")
    ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest) throws MessagingException, UnsupportedEncodingException {

        emailService.sendEmail(emailRequest);

        return null;
    }
}
