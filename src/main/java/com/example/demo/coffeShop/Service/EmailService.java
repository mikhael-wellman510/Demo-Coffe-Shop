package com.example.demo.coffeShop.Service;

import com.example.demo.coffeShop.DTO.Request.EmailRequest;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface EmailService {

    void sendEmail(EmailRequest emailRequest) throws MessagingException, UnsupportedEncodingException;
}
