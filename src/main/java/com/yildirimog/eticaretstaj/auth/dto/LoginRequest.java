package com.yildirimog.eticaretstaj.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Email cannot be blank")
        String username,

        @NotBlank(message = "Password cannot be blank")
        String password
) {
}
