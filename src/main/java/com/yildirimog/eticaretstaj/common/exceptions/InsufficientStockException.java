package com.yildirimog.eticaretstaj.common.exceptions;

public class InsufficientStockException extends RuntimeException{
    public InsufficientStockException(String message) {
        super(message);
    }
}
