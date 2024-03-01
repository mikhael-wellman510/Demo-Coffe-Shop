package com.example.demo.coffeShop.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "m_store")
@Builder(toBuilder = true)
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id ;

    @Column(name = "store_name" , nullable = false , length = 100 , unique = true)
    private String storeName;

    @Column(nullable = false , length = 100)
    private String address;

    @Column(name = "mobile_phone" , unique = true , length = 100 , nullable = false)
    private String mobilePhone;

    @Column(name = "created_at" , nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @Column(name = "is_active" , nullable = false)
    private Boolean isActive;

    @Override
    public String toString() {
        return "Store{" +
                "id='" + id + '\'' +
                ", storeName='" + storeName + '\'' +
                ", address='" + address + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", isActive=" + isActive +
                '}';
    }
}
