package com.example.demo.coffeShop.Controller;


import com.example.demo.coffeShop.Constant.AppPath;
import com.example.demo.coffeShop.DTO.Request.TransactionRequest;
import com.example.demo.coffeShop.DTO.Response.CommonResponse;
import com.example.demo.coffeShop.DTO.Response.TransactionResponse;
import com.example.demo.coffeShop.Service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.TRANSACTION)
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/add")
    ResponseEntity<?> createTransaction(@RequestBody TransactionRequest transactionRequest){

        TransactionResponse transactionResponse = transactionService.createTransaction(transactionRequest);

        if (transactionResponse !=null){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(CommonResponse.<TransactionResponse>builder()
                            .statusCode(HttpStatus.CREATED.value())
                            .message("Succes create Order")
                            .data(transactionResponse)
                            .build()
                    );
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(CommonResponse.<TransactionResponse>builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message("Not Found Order")

                        .build()
                );
    }

}
