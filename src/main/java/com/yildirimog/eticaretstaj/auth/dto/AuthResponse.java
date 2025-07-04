package com.yildirimog.eticaretstaj.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthResponse(
        String token,
        String username,
        String email
) {

}
