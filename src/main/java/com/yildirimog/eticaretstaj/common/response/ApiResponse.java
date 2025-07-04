package com.yildirimog.eticaretstaj.common.response;

public record ApiResponse<T>(
        boolean success,
        String message,
        T data
) {
    public static <T> ApiResponse<T> succes(String message,T data){
        return new ApiResponse<>(true, message, data);
    }
    public static <T> ApiResponse<T> failure(String message,T data){
        return new ApiResponse<>(false, message,data);
    }
}
