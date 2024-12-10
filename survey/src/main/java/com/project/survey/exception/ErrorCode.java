package com.project.survey.exception;

import lombok.*;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    // common errors
    UNEXPECTED_ERROR("SURVEY99999", "Unexpected error"),

    // member
    MEMBER_NOT_FOUND("MEMBER00001", "Member not found"),
    MEMBER_JWT_ALREADY_EXIST("MEMBER00002", "Member JWT already exist"),
    MEMBER_REQUIRED_VALUE("MEMBER00003", "Member required value"),
    MEMBER_EMAIL_NOT_MATCH("MEMBER00004", "Member getEmail not match"),
    MEMBER_INNER_EMAIL_NOT_FOUND("MEMBER00005", "Member inner getEmail not found"),
    MEMBER_EMAIL_PARAM_NOT_FOUND("MEMBER00006", "Member getEmail param not found"),
    CHANGE_EMAIL_VALUE_NOT_FOUND("MEMBER00007", "Change getEmail value not found"),
    
    // survey errors
    SURVEY_NOT_FOUND("SURVEY001", "Survey not found"),
    SURVEY_ID_IS_NULL("SURVEY002", "Survey ID is null"),

    //question errors
    FILE_UPLOAD_ERROR("FILE001", "File upload error"),
    FILE_DOWNLOAD_ERROR("FILE002", "File download error"),
    FILE_NOT_FOUND("FILE003", "File not found"),

    // refresh token
    REFRESH_TOKEN_NOT_HAVE_MEMBER("REF001", "Refresh token does not have a member"),
    REFRESH_TOKEN_EXPIRATION_DATE_NOT_FOUND("REF002", "Refresh token expiration date not found"),
    REFRESH_TOKEN_EXPIRED("REF003", "Refresh token expired"),
    DATA_NOT_FOUND("DATA001", "Data not found"),

    // security errors
    UNAUTHORIZED("UNAUTHORIZED", "Unauthorized"),
    AUTHENTICATION_ERROR("AUTHENTICATION_ERROR", "Authentication error"),
    PRINCIPAL_CAST_ERROR("PRINCIPAL_CAST_ERROR", "Principal cast error"),
    SECURITY_USER_NOT_FOUND("SEC001", "Security user not found"),
    PASSWORD_IS_NOT_HANDLE_IN_SECURITY_USER_DETAILS("SEC002", "Password is not handle in security user details"),
 
    //answer errors
    ANSWER_ALREADY_EXIST("ANSWER00001", "answer already exist"),
    ANSWER_QUESTION_NOT_MATCH("ANSWER00002", "answers and questions do not match"),
    ;

    private final String code;
    private final String message;
}
