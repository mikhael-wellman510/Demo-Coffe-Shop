package com.example.demo.coffeShop.Service.Impl;

import com.example.demo.coffeShop.Entity.ProductPicture;
import com.example.demo.coffeShop.Repositori.ProductPictureRepositori;
import com.example.demo.coffeShop.Service.ProductPictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductPictureServiceImpl implements ProductPictureService {

    private final ProductPictureRepositori productPictureRepositori;

    @Override
    public ProductPicture addProductPicture(ProductPicture productPicture) {


        return productPictureRepositori.saveAndFlush(productPicture);
    }

    @Override
    public ProductPicture editProductPicture(String id , String urlImage) {
       ProductPicture productPicture = productPictureRepositori.findById(id).orElse(null);

       if (productPicture != null){
           productPicture.setId(id);
           productPicture.setProductProfileUrl(urlImage);
           productPicture.setUpdatedAt(LocalDateTime.now());
           productPictureRepositori.save(productPicture);
           System.out.println(" succes edit gambar");
           return productPicture;

       }

       return null;
    }

    @Override
    public Boolean softDeletedProfilPicture(String id) {
        return null;
    }
}
