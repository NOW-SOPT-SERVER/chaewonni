package org.sopt.daangnMarket.exception;

public class NotFoundException extends RuntimeException {

    private final ApiErrorCode errorCode;

    public NotFoundException(ApiErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiErrorCode getErrorCode() {
        return errorCode;
    }
}
