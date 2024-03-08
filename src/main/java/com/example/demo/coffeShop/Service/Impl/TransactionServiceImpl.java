package com.example.demo.coffeShop.Service.Impl;

import com.example.demo.coffeShop.DTO.Request.TransactionDetailRequest;
import com.example.demo.coffeShop.DTO.Request.TransactionRequest;
import com.example.demo.coffeShop.DTO.Response.*;
import com.example.demo.coffeShop.Entity.*;
import com.example.demo.coffeShop.Entity.JWT.Customer;
import com.example.demo.coffeShop.Repositori.TransactionDetailRepositori;
import com.example.demo.coffeShop.Service.JwtService.CustomerService;
import com.example.demo.coffeShop.Service.ProductPriceService;
import com.example.demo.coffeShop.Service.TransactionService;
import com.example.demo.coffeShop.Service.TrxService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TrxService trxService;
    private final CustomerService customerService;
    private final ProductPriceService productPriceService;
    private final TransactionDetailRepositori transactionDetailRepositori;
    @Override
    public TransactionResponse createTransaction(TransactionRequest transactionRequest) {

        // Todo -> Find Customer Id
        Customer customer = customerService.getCustomerById(transactionRequest.getCustomerId());



        // Todo -> add Transaction
        Transaction transaction = Transaction.builder()
                .transactionDate(LocalDateTime.now())

                .customer(customer)
                .build();

        Transaction transactions = trxService.addTrx(transaction);

        //Todo -> untuk menampung data response
        List<TransactionDetailResponse>transactionDetailResponses =new ArrayList<>();
        Integer totalPrice = 0;
        // Todo -> Add Product Price
        for (TransactionDetailRequest tdr : transactionRequest.getTransactionDetail()){

            // Todo FindProductPrices by Id
            ProductPriceResponse productPrices = productPriceService.getProductById(tdr.getProductPriceId());


            ProductPrices pp = ProductPrices.builder()
                    .id(tdr.getProductPriceId())
                    .price(productPrices.getPrice())
                    .stock(productPrices.getStock())
                    .product(Product.builder()
                            .id(productPrices.getProductResponse().getId())
                            .createdAt(productPrices.getProductResponse().getCreatedAt())
                            .kategori(productPrices.getProductResponse().getKategori())
                            .productName(productPrices.getProductResponse().getProductName())
                            .productPicture(ProductPicture.builder()
                                    .id(productPrices.getProductResponse().getProductPictureResponse().getId())
                                    .uploadAt(productPrices.getProductResponse().getProductPictureResponse().getUploadAt())
                                    .productProfileUrl(productPrices.getProductResponse().getProductPictureResponse().getImageUrl())
                                    .build())
                            .build())
                    .store(Store.builder()
                            .id(productPrices.getStoreResponse().getId())
                            .storeName(productPrices.getStoreResponse().getStoreName())
                            .address(productPrices.getStoreResponse().getAddress())
                            .mobilePhone(productPrices.getStoreResponse().getMobilePhone())
                            .isActive(productPrices.getStoreResponse().getIsActive())
                            .createdAt(productPrices.getProductResponse().getCreatedAt())
                            .updatedAt(productPrices.getProductResponse().getUpdateAt())
                            .build())
                    .build();
            // Todo Add transaction


            TransactionDetail transactionDetail = TransactionDetail.builder()
                    .quantity(tdr.getQty())
                    .transaction(transactions)
                    .productPrices(pp)
                    .build();

         TransactionDetail td =   transactionDetailRepositori.save(transactionDetail);

         transactionDetailResponses.add(TransactionDetailResponse.builder()
                         .id(td.getId())
                         .qty(td.getQuantity())
                         .productName(td.getProductPrices().getProduct().getProductName())
                         .price(td.getProductPrices().getPrice())
                 .build());

         totalPrice = totalPrice + td.getProductPrices().getPrice();

        }


     return  TransactionResponse.builder()
             .transactionId(transactions.getId())
             .transactionDate(LocalDateTime.now())
             .customerResponse(CustomerResponse.builder()
                     .id(customer.getId())
                     .nama(customer.getNama())
                     .noHp(customer.getNoHP())
                     .address(customer.getNoHP())
                     .email(customer.getEmail())
                     .build())

             .transactionDetailResponses(transactionDetailResponses)
             .totalPrice(totalPrice)
             .build();
    }
}
