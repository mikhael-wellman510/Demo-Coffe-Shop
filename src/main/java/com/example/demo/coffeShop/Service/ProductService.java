package com.example.demo.coffeShop.Service;

import com.example.demo.coffeShop.Entity.Product;

public interface ProductService {

    Product addProduct(Product product);
    Boolean softDelete(String id);

    Product editProduct(Product product);


}
