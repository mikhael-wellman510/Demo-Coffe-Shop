package com.example.demo.coffeShop.Service.Impl;

import com.example.demo.coffeShop.DTO.Request.StoreRequest;
import com.example.demo.coffeShop.DTO.Response.StoreResponse;
import com.example.demo.coffeShop.Entity.Store;
import com.example.demo.coffeShop.Repositori.StoreRepositori;
import com.example.demo.coffeShop.Service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepositori storeRepositori;

    @Override
    public StoreResponse addStore(StoreRequest storeRequest) {
        System.out.println(storeRequest);
        Store store = Store.builder()
                .storeName(storeRequest.getStoreName())
                .address(storeRequest.getAddress())
                .mobilePhone(storeRequest.getMobilePhone())
                .isActive(Boolean.TRUE)
                .createdAt(LocalDateTime.now())
                .build();


        Store saveStore = storeRepositori.saveAndFlush(store);

        StoreResponse storeResponse = StoreResponse.builder()
                .id(saveStore.getId())
                .storeName(saveStore.getStoreName())
                .address(saveStore.getAddress())
                .mobilePhone(saveStore.getMobilePhone())
                .isActive(store.getIsActive())
                .createdAt(saveStore.getCreatedAt())
                .updatedAt(saveStore.getUpdatedAt())
                .build();

        return storeResponse;
    }

    @Override
    public List<StoreResponse> findAllStore() {

        List<Store> store = storeRepositori.findAllByIsActiveTrue();

        if (!store.isEmpty()){
            return store.stream().map(stores -> StoreResponse.builder()
                    .id(stores.getId())
                    .storeName(stores.getStoreName())
                    .address(stores.getAddress())
                    .mobilePhone(stores.getMobilePhone())
                    .isActive(stores.getIsActive())
                    .createdAt(stores.getCreatedAt())
                    .updatedAt(stores.getUpdatedAt())
                    .build()).toList();
        }

        return null;

    }

    @Override
    public StoreResponse findStoreById(String id) {

        Store store = storeRepositori.findById(id).orElse(null);

        if (store != null){

            return StoreResponse.builder()
                    .id(store.getId())
                    .storeName(store.getMobilePhone())
                    .address(store.getAddress())
                    .mobilePhone(store.getMobilePhone())
                    .isActive(store.getIsActive())
                    .createdAt(store.getCreatedAt())
                    .updatedAt(store.getUpdatedAt())
                    .build();
        }

        return null;
    }

    @Override
    public StoreResponse updateStore(StoreRequest storeRequest) {

        // Todo -> Find Store Id
        Store store = storeRepositori.findById(storeRequest.getId()).orElse(null);

        // Todo -> Mapping data Terbaru
        Store updateStore = Store.builder()
                .id(store.getId())
                .storeName(storeRequest.getStoreName())
                .address(storeRequest.getAddress())
                .mobilePhone(storeRequest.getMobilePhone())
                .isActive(store.getIsActive())
                .createdAt(store.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();

        // Todo -> Save To Database

        storeRepositori.saveAndFlush(updateStore);

        if (store != null){
            return StoreResponse.builder()
                    .id(updateStore.getId())
                    .storeName(updateStore.getStoreName())
                    .address(updateStore.getAddress())
                    .mobilePhone(updateStore.getMobilePhone())
                    .isActive(updateStore.getIsActive())
                    .createdAt(store.getCreatedAt())
                    .updatedAt(LocalDateTime.now())
                    .build();
        }

        return null;

    }

    @Override
    public Boolean deleteStore(String id) {

        // Todo -> Search id
        Store store = storeRepositori.findById(id).orElse(null);
        System.out.println("hasil" + store);
        if (store != null){
            store.setIsActive(Boolean.FALSE);
            storeRepositori.saveAndFlush(store);
            return true;
        }

        return false;
    }
}
