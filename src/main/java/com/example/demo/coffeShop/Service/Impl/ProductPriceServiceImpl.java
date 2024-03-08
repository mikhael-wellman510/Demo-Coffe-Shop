package com.example.demo.coffeShop.Service.Impl;

import com.example.demo.coffeShop.DTO.Request.ProductPriceRequest;
import com.example.demo.coffeShop.DTO.Response.ProductPictureResponse;
import com.example.demo.coffeShop.DTO.Response.ProductPriceResponse;
import com.example.demo.coffeShop.DTO.Response.ProductResponse;
import com.example.demo.coffeShop.DTO.Response.StoreResponse;
import com.example.demo.coffeShop.Entity.Product;
import com.example.demo.coffeShop.Entity.ProductPicture;
import com.example.demo.coffeShop.Entity.ProductPrices;
import com.example.demo.coffeShop.Entity.Store;
import com.example.demo.coffeShop.Repositori.ProductPriceRepositori;
import com.example.demo.coffeShop.Service.ProductPictureService;
import com.example.demo.coffeShop.Service.ProductPriceService;
import com.example.demo.coffeShop.Service.ProductService;
import com.example.demo.coffeShop.Service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class ProductPriceServiceImpl implements ProductPriceService {

    private final ProductPriceRepositori productPriceRepositori;
    private final ProductPictureService productPictureService;
    private final ProductService productService;
    private final StoreService storeService;

    @Override
    public ProductPriceResponse addProductPrice(ProductPriceRequest productPriceRequest) throws IOException {


        // Todo - > Class untuk method2 Upload profil ke firebase
        UploadImage uploadImage = new UploadImage();

        String fileName = productPriceRequest.getImages().getOriginalFilename();
        fileName = UUID.randomUUID().toString().concat(uploadImage.getExtension(fileName));
        File file = uploadImage.convertToFile(productPriceRequest.getImages(), fileName);

        // Todo -> ini string yg di upload ke Databse
        String url = uploadImage.uploadFIle(file, fileName);
        file.delete();


        // Todo -> Add Product Picture
        ProductPicture productPicture = ProductPicture.builder()
                .productProfileUrl(url)
                .uploadAt(LocalDateTime.now())
                .build();

        ProductPicture saveProductPicture = productPictureService.addProductPicture(productPicture);


        // Todo -> Add m_product


        Product addProduct = Product.builder()
                .productName(productPriceRequest.getProductName())
                .kategori(productPriceRequest.getKategori())
                .productPicture(saveProductPicture)
                .createdAt(LocalDateTime.now())
                .build();

        Product saveProduct = productService.addProduct(addProduct);

        // Todo -> get Store id

        StoreResponse storeResponse = storeService.findStoreById(productPriceRequest.getStoreId());

        Store store = Store.builder()
                .id(storeResponse.getId())
                .storeName(storeResponse.getStoreName())
                .address(storeResponse.getAddress())
                .mobilePhone(storeResponse.getMobilePhone())
                .isActive(storeResponse.getIsActive())
                .createdAt(storeResponse.getCreatedAt())
                .updatedAt(storeResponse.getUpdatedAt())
                .build();


        // Todo -> addProductPrices
        ProductPrices productPrices = ProductPrices.builder()
                .price(productPriceRequest.getPrice())
                .stock(productPriceRequest.getStock())
                .isActive(Boolean.TRUE)
                .createdAt(LocalDateTime.now())
                .product(saveProduct)
                .store(store)
                .build();

        ProductPrices saveProductPrice = productPriceRepositori.saveAndFlush(productPrices);

        if (productPrices != null) {
            return ProductPriceResponse.builder()
                    .id(saveProductPrice.getId())
                    .price(saveProductPrice.getPrice())
                    .stock(saveProductPrice.getStock())
                    .productResponse(ProductResponse.builder()
                            .id(addProduct.getId())
                            .createdAt(LocalDateTime.now())
                            .kategori(addProduct.getKategori())
                            .productName(addProduct.getProductName())
                            .productPictureResponse(ProductPictureResponse.builder()
                                    .id(saveProductPicture.getId())
                                    .uploadAt(LocalDateTime.now())
                                    .imageUrl(saveProductPicture.getProductProfileUrl())
                                    .build())
                            .build())
                    .storeResponse(StoreResponse.builder()
                            .id(storeResponse.getId())
                            .storeName(storeResponse.getStoreName())
                            .address(storeResponse.getAddress())
                            .mobilePhone(storeResponse.getMobilePhone())
                            .isActive(storeResponse.getIsActive())
                            .createdAt(storeResponse.getCreatedAt())
                            .updatedAt(storeResponse.getUpdatedAt())
                            .build())
                    .build();


        }


        return null;
    }

    @Override
    public List<ProductPriceResponse> findAllProductPrices() {

        // Todo -> Find Product Yg is Active nya true
        List<ProductPrices> getAll = productPriceRepositori.findAllByIsActiveTrue();

        if (getAll != null) {
            return getAll.stream().map(getAlls -> ProductPriceResponse.builder()
                    .id(getAlls.getId())
                    .price(getAlls.getPrice())
                    .stock(getAlls.getStock())
                    .productResponse(ProductResponse.builder()
                            .id(getAlls.getProduct().getId())
                            .createdAt(getAlls.getProduct().getCreatedAt())
                            .kategori(getAlls.getProduct().getKategori())
                            .productName(getAlls.getProduct().getProductName())
                            .productPictureResponse(ProductPictureResponse.builder()
                                    .id(getAlls.getProduct().getProductPicture().getId())
                                    .uploadAt(getAlls.getProduct().getProductPicture().getUploadAt())
                                    .imageUrl(getAlls.getProduct().getProductPicture().getProductProfileUrl())
                                    .build())
                            .build())
                    .storeResponse(StoreResponse.builder()
                            .id(getAlls.getStore().getId())
                            .storeName(getAlls.getStore().getStoreName())
                            .address(getAlls.getStore().getAddress())
                            .mobilePhone(getAlls.getStore().getMobilePhone())
                            .isActive(getAlls.getStore().getIsActive())
                            .createdAt(getAlls.getStore().getCreatedAt())
                            .updatedAt(getAlls.getStore().getUpdatedAt())
                            .build())
                    .build()).toList();
        }
        return null;
    }

    @Override
    public ProductPriceResponse getProductById(String id) {

        ProductPrices productPrices = productPriceRepositori.findById(id).orElse(null);

        if (productPrices != null){

            return ProductPriceResponse.builder()
                    .id(productPrices.getId())
                    .price(productPrices.getPrice())
                    .stock(productPrices.getStock())
                    .productResponse(ProductResponse.builder()
                            .id(productPrices.getId())
                            .createdAt(productPrices.getProduct().getCreatedAt())
                            .kategori(productPrices.getProduct().getKategori())
                            .productName(productPrices.getProduct().getProductName())
                            .productPictureResponse(ProductPictureResponse.builder()
                                    .id(productPrices.getProduct().getProductPicture().getId())
                                    .uploadAt(productPrices.getProduct().getProductPicture().getUploadAt())
                                    .imageUrl(productPrices.getProduct().getProductPicture().getProductProfileUrl())
                                    .build())
                            .build())
                    .storeResponse(StoreResponse.builder()
                            .id(productPrices.getStore().getId())
                            .storeName(productPrices.getStore().getStoreName())
                            .address(productPrices.getStore().getAddress())
                            .mobilePhone(productPrices.getStore().getMobilePhone())
                            .isActive(productPrices.getIsActive())
                            .createdAt(productPrices.getStore().getCreatedAt())
                            .updatedAt(productPrices.getStore().getUpdatedAt())
                            .build())
                    .build();
        }

        return null;
    }

    @Override
    public ProductPriceResponse editProductPrice(ProductPriceRequest productPriceRequest) throws IOException {
        // Todo -> Konsep edit , kalau id nya ada , dia akan update
        // todo -> findProductPrice Id

        ProductPrices productPrices = productPriceRepositori.findById(productPriceRequest.getId()).orElse(null);



        // Todo -> Edit Picture Product

        UploadImage uploadImage = new UploadImage();

        String fileName = productPriceRequest.getImages().getOriginalFilename();
        fileName = UUID.randomUUID().toString().concat(uploadImage.getExtension(fileName));
        File file = uploadImage.convertToFile(productPriceRequest.getImages(), fileName);

        // Todo -> ini string yg di upload ke Databse
        String url = uploadImage.uploadFIle(file, fileName);
        file.delete();


        ProductPicture saveProductPicture = productPictureService.editProductPicture(productPrices.getProduct().getProductPicture().getId() , url);


        // Todo -> Edit M Product
            Product product = Product.builder()
                    .id(productPrices.getProduct().getId())
                    .productName(productPriceRequest.getProductName())
                    .kategori(productPriceRequest.getKategori())
                    .productPicture(saveProductPicture)
                    .createdAt(productPrices.getProduct().getCreatedAt())
                    .updatedAt(LocalDateTime.now())
                    .build();

            Product editProduct = productService.editProduct(product);

        // Todo -> Edit M Store




        // Todo -> Product Prices

        // Todo -> Jika harga nya beda
        if (!productPrices.getPrice().equals(productPriceRequest.getPrice()) ){
            System.out.println("Harga beda");
            ProductPrices productPricesSave = ProductPrices.builder()
                    .id(productPriceRequest.getId())
                    .price(productPrices.getPrice())
                    .stock(productPrices.getStock())
                    .isActive(Boolean.FALSE)
                    .createdAt(productPrices.getCreatedAt())
                    .product(productPrices.getProduct())
                    .store(productPrices.getStore())
                    .build();

            productPriceRepositori.saveAndFlush(productPricesSave);


            //Todo -> Create new Product Price karena harga baru
            ProductPrices productPricesNew = ProductPrices.builder()
                    .price(productPriceRequest.getPrice())
                    .stock(productPriceRequest.getStock())
                    .isActive(Boolean.TRUE)
                    .createdAt(LocalDateTime.now())
                    .product(editProduct)
                    .store(productPrices.getStore())
                    .build();

            ProductPrices newPP = productPriceRepositori.saveAndFlush(productPricesNew);

             return ProductPriceResponse.builder()
                     .id(newPP.getId())
                     .price(newPP.getPrice())
                     .stock(newPP.getStock())
                     .productResponse(ProductResponse.builder()
                             .id(editProduct.getId())
                             .createdAt(editProduct.getCreatedAt())
                             .kategori(editProduct.getKategori())
                             .productName(editProduct.getProductName())
                             .productPictureResponse(ProductPictureResponse.builder()
                                     .id(saveProductPicture.getId())
                                     .uploadAt(saveProductPicture.getUploadAt())
                                     .updatedAt(LocalDateTime.now())
                                     .imageUrl(saveProductPicture.getProductProfileUrl())
                                     .build())
                             .build())
                     .storeResponse(StoreResponse.builder()
                             .id(productPrices.getStore().getId())
                             .storeName(productPrices.getStore().getStoreName())
                             .address(productPrices.getStore().getAddress())
                             .mobilePhone(productPrices.getStore().getMobilePhone())
                             .createdAt(productPrices.getStore().getCreatedAt())
                             .updatedAt(productPrices.getStore().getUpdatedAt())
                             .isActive(productPrices.getStore().getIsActive())
                             .build())
                     .build();

        }


        // Todo -> Kalau harga nya sama
        ProductPrices productPricesSave = ProductPrices.builder()
                .id(productPrices.getId())
                .price(productPrices.getPrice())
                .stock(productPriceRequest.getStock())
                .isActive(productPrices.getIsActive())
                .createdAt(productPrices.getCreatedAt())
                .product(editProduct)
                .store(productPrices.getStore())
                .build();

        ProductPrices ppSame = productPriceRepositori.saveAndFlush(productPricesSave);





        return ProductPriceResponse.builder()
                .id(ppSame.getId())
                .price(ppSame.getPrice())
                .stock(ppSame.getStock())
                .productResponse(ProductResponse.builder()
                        .id(editProduct.getId())
                        .createdAt(editProduct.getCreatedAt())
                        .kategori(editProduct.getKategori())
                        .productName(editProduct.getProductName())
                        .productPictureResponse(ProductPictureResponse.builder()
                                .id(saveProductPicture.getId())
                                .uploadAt(saveProductPicture.getUploadAt())
                                .updatedAt(LocalDateTime.now())
                                .imageUrl(saveProductPicture.getProductProfileUrl())
                                .build())
                        .build())
                .storeResponse(StoreResponse.builder()
                        .id(productPrices.getStore().getId())
                        .storeName(productPrices.getStore().getStoreName())
                        .address(productPrices.getStore().getAddress())
                        .mobilePhone(productPrices.getStore().getMobilePhone())
                        .createdAt(productPrices.getStore().getCreatedAt())
                        .updatedAt(productPrices.getStore().getUpdatedAt())
                        .isActive(productPrices.getStore().getIsActive())
                        .build())
                .build();
    }

    @Override
    public Boolean deleteProductPrice(String id) {

        ProductPrices productPrices= productPriceRepositori.findById(id).orElse(null);

        if (productPrices!= null){
            productPrices.setIsActive(Boolean.FALSE);
            productPriceRepositori.saveAndFlush(productPrices);
            // Todo -> Balikan nya harus Boolean
            return true;
        }

        // Todo -> Balikan nya harus Boolean
        return false;
    }

    @Override
    public Page<ProductPriceResponse> getAllByPagging(Integer page, Integer size) {

        // Todo -> page 0 data 3 (dari Controller)
        Pageable pageable = PageRequest.of(page,size);

        // Todo -> Search Data dari IsActive True
        // Todo -> Buat Lagi di Repositori
        Page<ProductPrices> productPricesPage = productPriceRepositori.findAllByIsActiveTrue(pageable);

        List<ProductPriceResponse> productPriceResponses = new ArrayList<>();

        for (ProductPrices data : productPricesPage.getContent()){
            ProductPriceResponse productPriceResponse = ProductPriceResponse.builder()
                    .id(data.getId())
                    .price(data.getPrice())
                    .stock(data.getStock())
                    .productResponse(ProductResponse.builder()
                            .id(data.getProduct().getId())
                            .createdAt(data.getProduct().getCreatedAt())
                            .kategori(data.getProduct().getKategori())
                            .productName(data.getProduct().getProductName())
                            .productPictureResponse(ProductPictureResponse.builder()
                                    .id(data.getProduct().getProductPicture().getId())
                                    .uploadAt(data.getProduct().getProductPicture().getUploadAt())
                                    .updatedAt(data.getProduct().getProductPicture().getUpdatedAt())
                                    .imageUrl(data.getProduct().getProductPicture().getProductProfileUrl())
                                    .build())
                            .build())
                    .storeResponse(StoreResponse.builder()
                            .id(data.getStore().getId())
                            .storeName(data.getStore().getStoreName())
                            .address(data.getStore().getAddress())
                            .build())
                    .build();

            productPriceResponses.add(productPriceResponse);
        }
        return new PageImpl<>(productPriceResponses,pageable,productPricesPage.getTotalElements());
    }
}
