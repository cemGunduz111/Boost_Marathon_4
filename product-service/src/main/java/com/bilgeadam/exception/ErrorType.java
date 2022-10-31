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
    PRODUCT_NOT_UPDATED(1005, "Ürün güncellenemedi", INTERNAL_SERVER_ERROR),
    PRODUCT_NOT_CREATED(1006, "Ürün kaydedilemedi", INTERNAL_SERVER_ERROR),
    PRODUCT_NOT_DELETED(1007, "Ürün silinemedi", INTERNAL_SERVER_ERROR);
    private int code;
    private String message;
    HttpStatus httpStatus;

}
