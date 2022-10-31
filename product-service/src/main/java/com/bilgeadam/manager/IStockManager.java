package com.bilgeadam.manager;

import com.bilgeadam.dto.request.CreateStockRequestDto;
import com.bilgeadam.dto.request.UpdateStockRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bilgeadam.constants.ApiUrls.*;

@FeignClient(name = "stock-service", url = "${myapplication.feign.stock}/stock", decode404 = true)
public interface IStockManager {

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createProduct(@RequestBody CreateStockRequestDto dto);

    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateProduct(@RequestBody UpdateStockRequestDto dto);

    @DeleteMapping(DELETE)
    public ResponseEntity<Boolean> deleteProduct(@PathVariable(value="id") Long id);

}
