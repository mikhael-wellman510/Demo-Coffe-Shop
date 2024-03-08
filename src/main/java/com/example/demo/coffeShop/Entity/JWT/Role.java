package com.example.demo.coffeShop.Entity.JWT;


import com.example.demo.coffeShop.Constant.JWT.Erole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "m_role")
@Builder(toBuilder = true)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    private Erole role;

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", role=" + role +
                '}';
    }
}
