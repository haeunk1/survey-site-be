package com.project.servey.exception;

import lombok.Getter;

@Getter
public class ServeyException extends RuntimeException{
    private final ErrorCode errorCode;

    public ServeyException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ServeyException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }
}
