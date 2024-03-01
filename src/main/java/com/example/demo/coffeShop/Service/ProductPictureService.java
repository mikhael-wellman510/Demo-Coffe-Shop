package com.example.demo.coffeShop.Service;

import com.example.demo.coffeShop.Entity.ProductPicture;

public interface ProductPictureService {

    ProductPicture addProductPicture(ProductPicture productPicture);
    Boolean softDeletedProfilPicture(String id);

    ProductPicture editProductPicture(String id , String urlImage);
}
