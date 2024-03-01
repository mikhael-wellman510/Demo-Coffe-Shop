package com.example.demo.coffeShop.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "m_product_picture")
@Builder(toBuilder = true)
public class ProductPicture {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "product_profile_url" , nullable = false , length = 200)
    private String productProfileUrl;

    @Column(name = "upload_at" , nullable = false)
    private LocalDateTime uploadAt;


    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "ProductPicture{" +
                "id='" + id + '\'' +
                ", productProfileUrl='" + productProfileUrl + '\'' +
                ", uploadAt=" + uploadAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
