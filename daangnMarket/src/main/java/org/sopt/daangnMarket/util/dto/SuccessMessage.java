package org.sopt.daangnMarket.util.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum SuccessMessage {

    MEMBER_CREATE_SUCCESS(HttpStatus.CREATED.value(), "멤버 생성이 완료되었습니다."),
    ITEM_CREATE_SUCCESS(HttpStatus.CREATED.value(), "상품 등록이 완료되었습니다."),
    BOOKMARK_CREATE_SUCCESS(HttpStatus.CREATED.value(), "상품 좋아요가 완료되었습니다."),

    MEMBER_FIND_SUCCESS(HttpStatus.OK.value(), "멤버 조회가 완료되었습니다."),
    ITEMS_FIND_SUCCESS(HttpStatus.OK.value(), "지역별 상품 전체 조회가 완료되었습니다.");

    private final int status;
    private final String message;
}
