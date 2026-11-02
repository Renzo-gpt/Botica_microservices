package com.botica.common.web.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        String timestamp,
        int status,
        String error,
        String message,
        List<String> details
) {
    public static ErrorResponse of(int status, String message, List<String> details) {
        return new ErrorResponse(
                LocalDateTime.now().toString(),
                status,
                status >= 500 ? "Internal Error" : "Client Error",
                message,
                details != null ? details : List.of()
        );
    }
}