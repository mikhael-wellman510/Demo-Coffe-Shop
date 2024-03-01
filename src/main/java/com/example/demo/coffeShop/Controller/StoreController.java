package com.example.demo.coffeShop.Controller;


import com.example.demo.coffeShop.Constant.AppPath;
import com.example.demo.coffeShop.DTO.Response.CommonResponse;
import com.example.demo.coffeShop.DTO.Request.StoreRequest;
import com.example.demo.coffeShop.DTO.Response.StoreResponse;
import com.example.demo.coffeShop.Service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.STORE)
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/addStore")
    ResponseEntity<?> addStore(@RequestBody StoreRequest storeRequest){
        StoreResponse storeResponse = storeService.addStore(storeRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<StoreResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Succes Created Store")
                        .data(storeResponse)
                        .build()
                );

    }

    @GetMapping("/findAll")
    ResponseEntity<?> findAllStore(){
        List<StoreResponse> storeResponses = storeService.findAllStore();

        if (storeResponses != null){
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(CommonResponse.<List<StoreResponse>>builder()
                            .statusCode(HttpStatus.FOUND.value())
                            .message("Success GetAll")
                            .data(storeResponses)
                            .build()
                    );
        }

        return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(CommonResponse.<List<StoreResponse>>builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message("Data Not Found")
                        .build()
                );


    }

    @GetMapping("/findById/{id}")
    ResponseEntity<?> findStoreById(@PathVariable String id){
        StoreResponse storeResponse = storeService.findStoreById(id);

        if (storeResponse != null){
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(CommonResponse.<StoreResponse>builder()
                            .statusCode(HttpStatus.FOUND.value())
                            .message("Success Get Data By Id")
                            .data(storeResponse)
                            .build()
                    );
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(CommonResponse.<StoreResponse>builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message("Not Found")
                        .data(storeResponse)
                        .build()
                );

    }

    @PutMapping(value = "/editStore")
    ResponseEntity<?> updateStore(@RequestBody StoreRequest storeRequest){

        StoreResponse storeResponse = storeService.updateStore(storeRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<StoreResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Succes Updated")
                        .data(storeResponse)
                        .build()
                );

    }

    @DeleteMapping(value = "/deleteStore/{id}")
    public ResponseEntity<?> softDeletedStore(@PathVariable String id){

        Boolean softDelete = storeService.deleteStore(id);

        if (softDelete){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CommonResponse.<Boolean>builder()
                            .statusCode(HttpStatus.OK.value())
                            .message("succes Deleted")
                            .build()
                    );
        }

        return  ResponseEntity.status(HttpStatus.CONFLICT)
                .body(CommonResponse.<Boolean>builder()
                        .statusCode(HttpStatus.CONFLICT.value())
                        .message("Deleted Failed")
                        .build()
                );
    }





}

