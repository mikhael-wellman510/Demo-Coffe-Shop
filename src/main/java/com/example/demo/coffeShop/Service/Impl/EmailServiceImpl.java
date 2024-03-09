package com.example.demo.coffeShop.Service.Impl;

import com.example.demo.coffeShop.DTO.Request.EmailRequest;
import com.example.demo.coffeShop.Service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor

public class EmailServiceImpl implements EmailService {


    @Autowired
    private JavaMailSender javaMailSender;



    @Override
    public void sendEmail(EmailRequest emailRequest) throws MessagingException, UnsupportedEncodingException {


        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("mikhaelwellman423@gmail.com" , "Mikhael Wellman");
        helper.setTo(emailRequest.getEmail());
        System.out.println("Hasil : " +  message);
        helper.setSubject("Hello From Springboot");
        helper.setText("<p>Hello,</p><p>This is a test email sent from Spring Boot.</p>" ,true);

        javaMailSender.send(message);



    }
}
