package com.example.demo.coffeShop.DTO.Response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PaggingResponse {
    private Integer currentPage;
    private Integer totalPage;
    private Integer size;

}
