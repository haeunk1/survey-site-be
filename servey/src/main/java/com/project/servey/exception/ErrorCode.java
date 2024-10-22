package com.project.servey.exception;

import lombok.*;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    // common errors
    UNEXPECTED_ERROR("SERVEY99999", "Unexpected error"),

    // member
    MEMBER_NOT_FOUND("LINK00001", "Member not found"),
    MEMBER_JWT_ALREADY_EXIST("LINK00002", "Member JWT already exist"),
    MEMBER_REQUIRED_VALUE("LINK00003", "Member required value"),
    MEMBER_EMAIL_NOT_MATCH("LINK00004", "Member getEmail not match"),
    MEMBER_INNER_EMAIL_NOT_FOUND("LINK00005", "Member inner getEmail not found"),
    MEMBER_EMAIL_PARAM_NOT_FOUND("LINK00006", "Member getEmail param not found"),
    CHANGE_EMAIL_VALUE_NOT_FOUND("LINK00007", "Change getEmail value not found"),
    
    // servey errors
    SERVEY_NOT_FOUND("SERVEY001", "Servey not found"),
    SERVEY_ID_IS_NULL("SERVEY002", "Servey ID is null"),

    //question errors
    FILE_UPLOAD_ERROR("FILE001", "File upload error"),
    FILE_DOWNLOAD_ERROR("FILE002", "File download error"),
    FILE_NOT_FOUND("FILE003", "File not found"),

    // refresh token
    REFRESH_TOKEN_NOT_HAVE_MEMBER("REF001", "Refresh token does not have a member"),
    REFRESH_TOKEN_EXPIRATION_DATE_NOT_FOUND("REF002", "Refresh token expiration date not found"),
    REFRESH_TOKEN_EXPIRED("REF003", "Refresh token expired"),
    DATA_NOT_FOUND("DATA001", "Data not found"),
    ;

    private final String code;
    private final String message;
}
