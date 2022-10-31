package com.bilgeadam.controller;

import com.bilgeadam.dto.request.CreateRequestDto;
import com.bilgeadam.dto.request.UpdateRequestDto;
import com.bilgeadam.dto.response.ProductResponseDto;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.ProductServiceException;
import com.bilgeadam.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bilgeadam.constants.ApiUrls.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(PRODUCT)
public class ProductController {

    private final ProductService productService;

    @GetMapping(GETALL)
    @Operation(summary = "Ürünleri listeleyen metot")
    public ResponseEntity<List<ProductResponseDto>> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }

    @PostMapping(CREATE)
    @Operation(summary = "Ürün kaydetme metodu")
    public ResponseEntity<Boolean> createProduct(@RequestBody CreateRequestDto dto){
        try {
            productService.createProduct(dto);
            return ResponseEntity.ok(true);
        }catch (Exception e){
        throw new ProductServiceException(ErrorType.PRODUCT_NOT_CREATED);
        }
    }

    @PutMapping(UPDATE)
    @Operation(summary = "Ürün güncelleştirme metodu")
    public ResponseEntity<Boolean> updateProduct(@RequestBody UpdateRequestDto dto){
        return ResponseEntity.ok(productService.updateProduct(dto));
    }

    @DeleteMapping(DELETE)
    @Operation(summary = "Ürün silme metodu")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable(value="id") Long id){
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
}
