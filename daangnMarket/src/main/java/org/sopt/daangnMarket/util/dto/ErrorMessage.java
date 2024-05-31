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

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error"),

    TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당하는 토큰이 존재하지 않습니다."),

    FORBIDDEN_MEMBER_ACCESS(HttpStatus.FORBIDDEN.value(), "이 멤버는 해당 상품에 대한 접근 권한이 없습니다."),
    JWT_UNAUTHORIZED_EXCEPTION(HttpStatus.UNAUTHORIZED.value(), "사용자의 로그인 검증을 실패했습니다."),
    TOKEN_INVALID_ERROR(HttpStatus.UNAUTHORIZED.value(), "토큰이 유효하지 않습니다.")

    ;

    private final int status;
    private final String message;
}
