package com.example.loginauthjwt.payload.request;

import lombok.Data;

@Data
public class SearchProdDTO {

    private Long price;
    private String prodCode;
    private String name;


}
