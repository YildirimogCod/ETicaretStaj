package com.yildirimog.eticaretstaj.auth.dto;

public record RegisterRequest (
        String username,
        String email,
        String password
){
}
