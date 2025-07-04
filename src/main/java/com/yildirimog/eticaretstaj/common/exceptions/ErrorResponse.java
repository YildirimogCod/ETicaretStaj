package com.yildirimog.eticaretstaj.common.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
public record ErrorResponse(
        int status,
        String message,
        LocalDateTime timestamp,
        String path
) {

}
