package org.sopt.daangnMarket.util.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 사용자가 존재하지 않습니다."),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 카테고리가 존재하지 않습니다."),
    LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 동네가 존재하지 않습니다."),
    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 상품이 존재하지 않습니다."),

    DUPLICATE_MEMBER(HttpStatus.CONFLICT.value(), "이미 가입한 회원입니다."),

    BOOKMARK_CONFLICT(HttpStatus.CONFLICT.value(), "이미 좋아요를 누르셨습니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error");

    private final int status;
    private final String message;
}
