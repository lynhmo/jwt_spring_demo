package com.example.loginauthjwt.security.services.Interface;

import com.example.loginauthjwt.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<Category> findById(long id);

    List<Category> findAll();

}
