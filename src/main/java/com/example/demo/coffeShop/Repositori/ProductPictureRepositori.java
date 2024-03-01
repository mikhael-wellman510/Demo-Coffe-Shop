package com.example.demo.coffeShop.Repositori;

import com.example.demo.coffeShop.Entity.ProductPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPictureRepositori extends JpaRepository<ProductPicture , String> {
}
