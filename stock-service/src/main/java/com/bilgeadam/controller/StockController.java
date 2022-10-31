package com.bilgeadam.controller;

import com.bilgeadam.dto.request.AddStockRequestDto;
import com.bilgeadam.dto.request.CreateStockRequestDto;
import com.bilgeadam.dto.request.UpdateStockRequestDto;
import com.bilgeadam.dto.response.StockResponseDto;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bilgeadam.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(STOCK)
public class StockController {

    private final StockService stockService;

    @GetMapping(GETALL)
    @Operation(summary = "Ürünleri listeleyen metot")
    public ResponseEntity<List<StockResponseDto>> getAllStock(){
        return ResponseEntity.ok(stockService.getAllStock());
    }

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createProduct(@RequestBody CreateStockRequestDto dto){
        return ResponseEntity.ok(stockService.createStock(dto));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateProduct(@RequestBody UpdateStockRequestDto dto){
        return ResponseEntity.ok(stockService.updateStock(dto));
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Boolean> deleteProduct(@PathVariable(value="id") Long id){
        return ResponseEntity.ok(stockService.deleteStock(id));
    }

    @PostMapping(UPDATESTOCK)
    @Operation(summary = "Stok bilgisini güncellemek için gerekli metot")
    public ResponseEntity<Boolean> addStock(@RequestBody AddStockRequestDto dto){
        return ResponseEntity.ok(stockService.addStock(dto));
    }
}
