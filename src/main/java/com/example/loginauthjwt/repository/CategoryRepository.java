package com.example.loginauthjwt.repository;

import com.example.loginauthjwt.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}