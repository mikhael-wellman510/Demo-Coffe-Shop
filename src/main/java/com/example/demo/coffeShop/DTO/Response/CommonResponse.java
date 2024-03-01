package com.example.demo.coffeShop.DTO.Response;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CommonResponse<T> {
    private Integer statusCode ;
    private String message ;
    private T data;
    private PaggingResponse paggingResponse;

}
