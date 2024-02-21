package com.example.loginauthjwt.controller;

import com.example.loginauthjwt.models.Product;
import com.example.loginauthjwt.payload.request.ProductReqDto;
import com.example.loginauthjwt.payload.request.SearchProdDTO;
import com.example.loginauthjwt.payload.response.BaseResDTO;
import com.example.loginauthjwt.payload.response.MessageResponse;
import com.example.loginauthjwt.repository.ProductRepository;
import com.example.loginauthjwt.security.services.ProductServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private final ProductRepository productRepository;
    private final ProductServiceImpl productService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllProduct() {
        try{
            if (productRepository.findAll().isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(new BaseResDTO("success",productRepository.findAll()),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> insertProduct(@Valid @RequestBody ProductReqDto request) {
        try {
            Product _product = productService.insertProduct(request);
            if (_product==null){
                return new ResponseEntity<>(new MessageResponse("CategoryId không hợp lệ!"),HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new BaseResDTO("success",_product),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new BaseResDTO("failure",e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTutorial(@PathVariable("id") long id,@Valid @RequestBody ProductReqDto product) {
        try {
            Product _product = productService.updateProduct(id,product);
            return new ResponseEntity<>(new BaseResDTO("success",_product), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTutorial(@PathVariable("id") long id) {
        try {
            String returnValue = productService.deleteProd(id);
            if (returnValue.equals("success")){
                return new ResponseEntity<>(new MessageResponse("Xóa product thành công!"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new MessageResponse("Xóa product thất bại!"),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchProduct(@RequestBody SearchProdDTO product) {
        try {
            List<Product> _product = productService.searchingProduct3(product);
            return new ResponseEntity<>(new BaseResDTO("success",_product), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
