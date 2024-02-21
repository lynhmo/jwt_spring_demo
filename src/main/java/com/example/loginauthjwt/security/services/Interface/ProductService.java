package com.example.loginauthjwt.security.services.Interface;


import com.example.loginauthjwt.models.Product;
import com.example.loginauthjwt.payload.request.SearchProdDTO;

import java.util.List;

public interface ProductService {
    List<Product> searchingProduct3(SearchProdDTO dto);
}
