package com.example.demo.coffeShop.Entity.JWT;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "m_customer")
@Builder(toBuilder = true)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 100)
    private String nama ;

    @Column(name = "no_hp" ,nullable = false , length = 100)
    private String noHP;

    @Column(nullable = false , length = 100 , unique = true)
    private String email;

    @Column(nullable = false , length = 100)
    private String address;

    @OneToOne
    @JoinColumn(name = "user_credential_id")
    private UserCredential userCredential;

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", nama='" + nama + '\'' +
                ", noHP='" + noHP + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", userCredential=" + userCredential +
                '}';
    }
}
