package org.sopt.daangnMarket.exception;

import org.springframework.http.HttpStatus;

public enum ApiErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Member not found"),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Category not found"),
    LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Location not found"),

    DUPLICATE_MEMBER(HttpStatus.CONFLICT.value(), "Duplicate member"),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error");

    private int status;
    private String message;

    ApiErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
