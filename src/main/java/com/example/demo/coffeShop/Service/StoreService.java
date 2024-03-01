package com.example.demo.coffeShop.Service;

import com.example.demo.coffeShop.DTO.Request.StoreRequest;
import com.example.demo.coffeShop.DTO.Response.StoreResponse;

import java.util.List;

public interface StoreService {
    StoreResponse addStore(StoreRequest storeRequest);
    List<StoreResponse> findAllStore();
    StoreResponse findStoreById(String id);
    StoreResponse updateStore(StoreRequest storeRequest);
    Boolean deleteStore(String id);
}
