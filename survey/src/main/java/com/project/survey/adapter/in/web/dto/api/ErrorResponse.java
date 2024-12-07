package com.project.survey.adapter.in.web.dto.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String errorCode;
    private String message;
    private List<String> errors;

    // factory method
    public static ErrorResponse of(String errorCode, String message, List<String> errors) {
        return new ErrorResponse(errorCode, message, errors);
    }

}