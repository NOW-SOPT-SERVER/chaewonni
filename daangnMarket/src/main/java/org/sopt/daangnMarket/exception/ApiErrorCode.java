package org.sopt.daangnMarket.exception;

public enum ApiErrorCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    CATEGORY_NOT_FOUND(404, "Category not found"),
    DUPLICATE_MEMBER(409, "Duplicate member"),
    INTERNAL_SERVER_ERROR(500, "Internal server error");

    private int code;
    private String message;

    ApiErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
