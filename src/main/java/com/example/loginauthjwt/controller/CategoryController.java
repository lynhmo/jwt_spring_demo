package com.example.loginauthjwt.controller;

import com.example.loginauthjwt.models.Category;
import com.example.loginauthjwt.payload.request.CategoryReqDTO;
import com.example.loginauthjwt.payload.response.BaseResDTO;
import com.example.loginauthjwt.payload.response.MessageResponse;
import com.example.loginauthjwt.repository.CategoryRepository;
import com.example.loginauthjwt.security.services.CategoryServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final CategoryServiceImpl categoryService;

    @PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody CategoryReqDTO request) {
        try {
            Category _category = categoryService.insertCategory(request);
            return new ResponseEntity<>(new BaseResDTO("success",_category),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new BaseResDTO("failure",e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        try {
            if (categoryService.findAll().isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(new BaseResDTO("success",categoryService.findAll()),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTutorial(@PathVariable("id") long id, @RequestBody CategoryReqDTO category) {
        try {
            Category _category =categoryService.updateCategory(id,category);
            if (_category==null){
                return new ResponseEntity<>(new MessageResponse("Category không tồn tại!"),HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new BaseResDTO("success",_category), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTutorial(@PathVariable("id") long id) {
        try {
            String returnValue = categoryService.deleteCategory(id);
            if (returnValue.equals("success")){
                return new ResponseEntity<>(new MessageResponse("Xóa category thành công!"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new MessageResponse("Xóa category thất bại!"),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
