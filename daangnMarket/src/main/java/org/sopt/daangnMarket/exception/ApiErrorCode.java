package org.sopt.daangnMarket.exception;

import org.springframework.http.HttpStatus;

public enum ApiErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 사용자가 존재하지 않습니다."),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 카테고리가 존재하지 않습니다."),
    LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 동네가 존재하지 않습니다."),
    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 상품이 존재하지 않습니다."),

    DUPLICATE_MEMBER(HttpStatus.CONFLICT.value(), "이미 가입한 회원입니다."),

    BOOKMARK_CONFLICT(HttpStatus.CONFLICT.value(), "이미 좋아요를 누르셨습니다."),

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
