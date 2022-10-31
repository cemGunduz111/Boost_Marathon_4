package com.bilgeadam.exception;

import lombok.Getter;

@Getter
public class StockServiceException extends RuntimeException{
    private final ErrorType errorType;

    public StockServiceException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public StockServiceException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}
