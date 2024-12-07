package com.project.survey.exception;

import lombok.Getter;

@Getter
public class SurveyException extends RuntimeException{
    private final ErrorCode errorCode;

    public SurveyException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public SurveyException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }
}
