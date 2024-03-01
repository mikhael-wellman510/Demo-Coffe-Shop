package com.example.demo.coffeShop.Service;

import com.example.demo.coffeShop.DTO.Request.ProductPriceRequest;
import com.example.demo.coffeShop.DTO.Response.ProductPriceResponse;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface ProductPriceService {

    ProductPriceResponse addProductPrice(ProductPriceRequest productPriceRequest) throws IOException;
    List<ProductPriceResponse> findAllProductPrices();

    ProductPriceResponse getProductById(String id);

    ProductPriceResponse editProductPrice(ProductPriceRequest productPriceRequest) throws IOException;
    Boolean deleteProductPrice(String id);

    Page<ProductPriceResponse> getAllByPagging(Integer page , Integer size);
}
