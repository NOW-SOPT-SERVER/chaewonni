package org.sopt.daangnMarket.exception;

public class DuplicateMemberException extends RuntimeException {
    private final ApiErrorCode errorCode;

    public DuplicateMemberException(ApiErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiErrorCode getErrorCode() {
        return errorCode;
    }
}
