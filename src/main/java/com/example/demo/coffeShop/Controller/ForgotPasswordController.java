package com.example.demo.coffeShop.Controller;


import com.example.demo.coffeShop.Constant.AppPath;
import com.example.demo.coffeShop.DTO.Response.CommonResponse;
import com.example.demo.coffeShop.Service.ForgotPasswordService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.FORGOT_PASSWORD)
public class ForgotPasswordController {

    private final ForgotPasswordService forgotPasswordService;

    @PutMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) throws MessagingException {

       String forgotPassword = forgotPasswordService.forgotPassword(email);

       if (forgotPassword == null){
           return ResponseEntity.status(HttpStatus.CONFLICT)
                   .body(CommonResponse.builder()
                           .statusCode(HttpStatus.BAD_REQUEST.value())
                           .message("Email Not Found")
                           .build()
                   );
       }

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Please Check Your Email or Spam")
                        .build()
                );
    }

    @PutMapping("/set-password")
    public ResponseEntity<?> setPassword(@RequestParam String token , @RequestHeader String newPassword){
        String setNewPass =  forgotPasswordService.setPassword(token,newPassword);

        if (setNewPass != null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CommonResponse.builder()
                            .statusCode(HttpStatus.OK.value())
                            .message("Succes Change Password")
                            .build()
                    );
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .message("Waktu Habis Atau , Forgot password Again")
                        .build()
                );

    }

}
