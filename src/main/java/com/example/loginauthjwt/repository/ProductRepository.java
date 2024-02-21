package com.example.loginauthjwt.repository;

import com.example.loginauthjwt.models.Product;
import com.example.loginauthjwt.payload.request.SearchProdDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryId(Long categoryId);

    @Query(value = """
            select * from loginauth.product
            where (:#{#request.prodCode} IS NULL OR product_code = :#{#request.prodCode})
            and (:#{#request.price} IS NULL OR price = :#{#request.price})
            and (:#{#request.name} IS NULL OR product.name like concat('%',:#{#request.name},'%'))
            """, nativeQuery = true)
    List<Product> search(SearchProdDTO request);
}