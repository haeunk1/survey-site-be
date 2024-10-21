package com.project.servey.exception;

import lombok.*;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    // common errors
    UNEXPECTED_ERROR("SERVEY99999", "Unexpected error"),

     // servey errors
    SERVEY_NOT_FOUND("SERVEY001", "Servey not found"),
    SERVEY_ID_IS_NULL("SERVEY002", "Servey ID is null"),

     //question errors
    FILE_UPLOAD_ERROR("FILE001", "File upload error"),
    FILE_DOWNLOAD_ERROR("FILE002", "File download error"),
    FILE_NOT_FOUND("FILE003", "File not found"),
    ;

    private final String code;
    private final String message;
}
