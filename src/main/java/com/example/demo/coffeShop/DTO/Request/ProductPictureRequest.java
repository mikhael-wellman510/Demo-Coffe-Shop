package com.example.demo.coffeShop.DTO.Request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProductPictureRequest {

    private MultipartFile image;
}
