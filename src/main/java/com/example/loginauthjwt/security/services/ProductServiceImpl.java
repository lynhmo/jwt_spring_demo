package com.example.loginauthjwt.security.services;

import com.example.loginauthjwt.models.Category;
import com.example.loginauthjwt.models.Product;
import com.example.loginauthjwt.payload.request.ProductReqDto;
import com.example.loginauthjwt.payload.request.SearchProdDTO;
import com.example.loginauthjwt.repository.CategoryRepository;
import com.example.loginauthjwt.repository.ProductRepository;
import com.example.loginauthjwt.security.services.Interface.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public Product insertProduct(ProductReqDto product) {
        Optional<Category> checkCategory = categoryRepository.findById(product.getCategoryId());
        if (checkCategory.isPresent()) {
            String createdBy = product.getCreatedBy();
            String updateBy = product.getUpdateBy();
            String dateTime = LocalDateTime.now().format(dateFormat); // current time
            String createAt = dateTime;
            String updateAt = dateTime;
            Long status = product.getStatus();
//            Category category = product.getCategoryId();
            String description = product.getDescription();
            String name = product.getName();
            Long price = product.getPrice();
            Long sold = 0L;
            String productCode = UUID.randomUUID().toString();
            return productRepository.save(new Product(createdBy, createAt, updateBy, updateAt,
                            status, checkCategory.get(), productCode, description, name, price, sold));
        } else {
            return null;
        }
    }

    public Product updateProduct(Long id, ProductReqDto product) {
        //Check product
        Product updateProd = productRepository.findById(id).orElseThrow(() -> null);
        //Check category
        Category _category = categoryRepository.findById(product.getCategoryId()).orElseThrow(() -> null);

        updateProd.setUpdateBy(product.getUpdateBy());
        String dateTime = LocalDateTime.now().format(dateFormat); // current time
        updateProd.setUpdateAt(dateTime);
        updateProd.setStatus(product.getStatus());
        updateProd.setCategory(_category);
        updateProd.setDescription(product.getDescription());
        updateProd.setName(product.getName());
        updateProd.setPrice(product.getPrice());
        return productRepository.save(updateProd);
    }

    public String deleteProd(Long id) {
        Optional<Product> res = productRepository.findById(id);
        if (res.isPresent()) {
            productRepository.deleteById(id);
            return "success";
        }
        return "failure";
    }

    @Override
    public List<Product> searchingProduct3(@RequestBody SearchProdDTO resProduct) {
        return productRepository.search(resProduct);
    }
}
