package com.example.demo.coffeShop.Controller;

import com.example.demo.coffeShop.Constant.AppPath;
import com.example.demo.coffeShop.Constant.Kategori;
import com.example.demo.coffeShop.DTO.Request.ProductPriceRequest;
import com.example.demo.coffeShop.DTO.Response.CommonResponse;
import com.example.demo.coffeShop.DTO.Response.PaggingResponse;
import com.example.demo.coffeShop.DTO.Response.ProductPriceResponse;
import com.example.demo.coffeShop.DTO.Response.StoreResponse;
import com.example.demo.coffeShop.Entity.ProductPrices;
import com.example.demo.coffeShop.Service.ProductPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.PRODUCT)
public class ProductPriceController {

    private final ProductPriceService productPriceService;

    @PostMapping(value = "/addProductPrice" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<?> addProductPrice(@RequestParam MultipartFile images , @RequestParam String productName , @RequestParam Kategori kategori , @RequestParam Integer price , @RequestParam Integer stock , @RequestParam String storeId) throws IOException {
        ProductPriceRequest productPriceRequest = ProductPriceRequest.builder()
                .images(images)
                .productName(productName)
                .kategori(kategori)
                .price(price)
                .stock(stock)
                .storeId(storeId)
                .build();

        ProductPriceResponse productPriceResponse = productPriceService.addProductPrice(productPriceRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<ProductPriceResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Succes Add Product")
                        .data(productPriceResponse)
                        .build()
                );
    };

    @PutMapping(value = "/editProduct")
    ResponseEntity<?> editProductPrice(@RequestParam MultipartFile images , @RequestParam String id , @RequestParam String productName , @RequestParam Kategori kategori , @RequestParam Integer price , @RequestParam Integer stock , @RequestParam String storeId) throws IOException {

        ProductPriceRequest productPriceRequest = ProductPriceRequest.builder()
                .id(id)
                .images(images)
                .productName(productName)
                .kategori(kategori)
                .price(price)
                .stock(stock)
                .storeId(storeId)
                .build();

        ProductPriceResponse productPriceResponse = productPriceService.editProductPrice(productPriceRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<ProductPriceResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Success Edit Data")
                        .data(productPriceResponse)
                        .build()
                );
    }

    @GetMapping(value = "/findById/{id}")
    ResponseEntity<?> getProductById(@PathVariable String id){

        ProductPriceResponse productPriceResponse = productPriceService.getProductById(id);

        if (productPriceResponse != null){

            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(CommonResponse.<ProductPriceResponse>builder()
                            .statusCode(HttpStatus.FOUND.value())
                            .message("Succes Get Data")
                            .data(productPriceResponse)
                            .build());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(CommonResponse.<ProductPriceResponse>builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message("Failed find data , Data Not Found")
                        .build());
    }

    @GetMapping(value = "/findAll")
    ResponseEntity<?> findAllProduct(){
        List<ProductPriceResponse> productPriceResponses = productPriceService.findAllProductPrices();

        if (!productPriceResponses.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(CommonResponse.<List<ProductPriceResponse>>builder()
                            .statusCode(HttpStatus.FOUND.value())
                            .message("Success Find All")
                            .data(productPriceResponses)
                            .build()
                    );
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(CommonResponse.<List<ProductPriceResponse>>builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message("Product is Empty")
                        .build()
                );

    }

    @DeleteMapping("/softDelete/{id}")
    ResponseEntity<?> softDelete(@PathVariable String id){
        Boolean productPrices = productPriceService.deleteProductPrice(id);

        if (productPrices){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CommonResponse.<Boolean>builder()
                            .statusCode(HttpStatus.OK.value())
                            .message("Success Deleted")
                            .build());

        }

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(CommonResponse.<Boolean>builder()
                        .statusCode(HttpStatus.CONFLICT.value())
                        .message("Failed Deleted")
                        .build()
                );
    }

    // Todo -> Pagination
    @GetMapping(value = "/productPrice")
    public ResponseEntity<?> getAllProductPaggination( @RequestParam(name = "page" , defaultValue = "0") Integer page , @RequestParam(name = "size", defaultValue = "5") Integer size){
        Page<ProductPriceResponse> productPriceResponses = productPriceService.getAllByPagging(page,size);

        // Todo - > Ini Untuk menapilkan posisi Page , Dan Size
        PaggingResponse paggingResponse = PaggingResponse.builder()
                .currentPage(page)
                .totalPage(productPriceResponses.getTotalPages())
                .size(size)
                .build();

        return ResponseEntity.status(HttpStatus.FOUND)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.FOUND.value())
                        .message("Succes")
                        // Todo -> hanya getContemt : supaya dapat data nya saja
                        .data(productPriceResponses.getContent())
                        .paggingResponse(paggingResponse)
                        .build()
                );
    }


}
