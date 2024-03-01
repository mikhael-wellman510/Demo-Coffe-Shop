package com.example.demo.coffeShop.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "m_transaction")
@Builder(toBuilder = true)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id ;

    @Column(name = "transaction_data" , nullable = false )
    private LocalDateTime transactionDate;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


}
