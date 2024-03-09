package com.example.demo.coffeShop.Service;

import jakarta.mail.MessagingException;

public interface ForgotPasswordService {
    String forgotPassword(String email) throws MessagingException;

    String setPassword(String email , String newPassword);
}
