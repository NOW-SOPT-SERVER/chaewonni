package org.sopt.daangnMarket.exception;

public class ConflictException extends RuntimeException {

    private final ApiErrorCode errorCode;

    public ConflictException(ApiErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiErrorCode getErrorCode() {
        return errorCode;
    }
}
