package com.example.demo.coffeShop.Entity;

import com.example.demo.coffeShop.Constant.Kategori;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "m_product")
@Builder(toBuilder = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "product_name" , nullable = false , length = 100 , unique = true)
    private String productName;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Kategori kategori;

    @ManyToOne
    @JoinColumn(name= "product_picture_id")
    private ProductPicture productPicture;

    @Column(name = "created_at" , nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", productName='" + productName + '\'' +
                ", kategori=" + kategori +
                ", productPicture=" + productPicture +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
