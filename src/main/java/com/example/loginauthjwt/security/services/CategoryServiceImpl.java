package com.example.loginauthjwt.security.services;

import com.example.loginauthjwt.models.Category;
import com.example.loginauthjwt.payload.request.CategoryReqDTO;
import com.example.loginauthjwt.repository.CategoryRepository;
import com.example.loginauthjwt.repository.ProductRepository;
import com.example.loginauthjwt.security.jwt.JwtUtils;
import com.example.loginauthjwt.security.services.Interface.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final JwtUtils jwtUtils;
    private final CategoryRepository categoryRepository;
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private final ProductRepository productRepository;

    @Override
    public Optional<Category> findById(long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category insertCategory(CategoryReqDTO category) {
        String createBy = category.getCreatedBy();// sau nay lay username nguoi tao qua token va cookie
        String updateBy = category.getUpdateBy();//
        String dateTime = LocalDateTime.now().format(dateFormat); // current time
        String createAt = dateTime;
        String updateAt = dateTime;
        Long status = category.getStatus();
        String description = category.getDescription();
        String name = category.getName();
        String categoryCode = UUID.randomUUID().toString();
        return categoryRepository.save(new Category(
                        name,categoryCode,status,description,createBy,updateBy,createAt,updateAt));
    }

    public Category updateCategory(Long id, CategoryReqDTO category) {
        Optional<Category> res = categoryRepository.findById(id);
        if (res.isPresent()) {
            Category updateCategory = res.get();
            updateCategory.setUpdateBy(category.getUpdateBy());
            String dateTime = LocalDateTime.now().format(dateFormat); // current time
            updateCategory.setUpdateAt(dateTime);
            updateCategory.setStatus(category.getStatus());
            updateCategory.setDescription(category.getDescription());
            updateCategory.setName(category.getName());
            return categoryRepository.save(updateCategory);
        }else{
            return null;
        }
    }

    public String deleteCategory(Long id) {
        Optional<Category> res = categoryRepository.findById(id);
        if (res.isPresent() && productRepository.findAllByCategoryId(id).isEmpty()) {
            categoryRepository.deleteById(id);
            return "success";
        }
        return "failure";
    }
}
