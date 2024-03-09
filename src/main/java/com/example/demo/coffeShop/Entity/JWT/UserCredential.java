package com.example.demo.coffeShop.Entity.JWT;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "m_user_credential")
@Builder(toBuilder = true)
public class UserCredential  {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private  Role role;

    @Override
    public String toString() {
        return "UserCredential{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
