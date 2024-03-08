package com.example.demo.coffeShop.Service.Impl;

import com.example.demo.coffeShop.Entity.Product;
import com.example.demo.coffeShop.Repositori.ProductRepositori;
import com.example.demo.coffeShop.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepositori productRepositori;

    @Override
    public Product addProduct(Product product) {
        return productRepositori.save(product);
    }

    @Override
    public Boolean softDelete(String id) {
        return null;
    }

    @Override
    public Product editProduct(Product product) {


        return productRepositori.saveAndFlush(product);
    }
}
