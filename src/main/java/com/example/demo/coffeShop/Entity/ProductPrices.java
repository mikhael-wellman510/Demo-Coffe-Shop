package com.example.demo.coffeShop.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "m_product_prices")
@Builder(toBuilder = true)
public class ProductPrices {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id ;

    @Column(nullable = false , length = 100)
    private Integer price;

    @Column(nullable = false , length = 100)
    private Integer stock;

    @Column(name = "is_active" , nullable = false)
    private Boolean isActive;

    @Column(name = "created_at" , nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Override
    public String toString() {
        return "ProductPrices{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", product=" + product +
                ", store=" + store +
                '}';
    }
}
