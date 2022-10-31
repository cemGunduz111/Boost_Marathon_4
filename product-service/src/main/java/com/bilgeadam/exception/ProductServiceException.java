package com.bilgeadam.exception;

import lombok.Getter;

@Getter
public class ProductServiceException extends RuntimeException{
    private final ErrorType errorType;

    public ProductServiceException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public ProductServiceException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}
