package com.example.demo.coffeShop.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_transaction_detail")
@Builder(toBuilder = true)
public class TransactionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false , length = 100)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "product_price_id")
    private ProductPrices productPrices;


}
