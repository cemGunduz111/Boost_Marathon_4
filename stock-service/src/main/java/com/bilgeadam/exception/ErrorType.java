package com.bilgeadam.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    INTERNAL_ERROR(2000, "Internal Server Error", INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(2001, "Invalid Parameter Error", BAD_REQUEST),

    USERNAME_DUPLICATE(1003, "Bu Kullanıcı adı zaten kullanılıyor.", INTERNAL_SERVER_ERROR),
    PRODUCT_NOT_FOUND(1004, "Ürün bulunamadı", INTERNAL_SERVER_ERROR),
    STOCK_NOT_ADDED(1005, "Stok değiştirilemedi", INTERNAL_SERVER_ERROR),

    PRODUCT_NOT_CREATED(1006, "Ürün kaydedilemedi", INTERNAL_SERVER_ERROR),
    STOCK_NOT_DEFINED(1007, "Stok eksi girilemez", INTERNAL_SERVER_ERROR),
    STOCK_NOT_UPDATED(1008, "Stok güncellenemedi", INTERNAL_SERVER_ERROR),
    STOCK_NOT_DELETED(1009, "Stok silinemedi", INTERNAL_SERVER_ERROR);
    private int code;
    private String message;
    HttpStatus httpStatus;

}
