package com.project.servey.exception;

import lombok.*;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    // common errors
    UNEXPECTED_ERROR("LINK99999", "Unexpected error"),

     // servey errors
     SERVEY_NOT_FOUND("SERVEY001", "Servey not found"),
     SERVEY_ID_IS_NULL("SERVEY002", "Servey ID is null"),
    ;

    private final String code;
    private final String message;
}
