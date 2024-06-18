package org.sopt.practice.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum SuccessMessage {

    MEMBER_CREATE_SUCCESS(HttpStatus.CREATED.value(), "멤버 생성이 완료되었습니다."),
    BLOG_CREATE_SUCCESS(HttpStatus.CREATED.value(),"블로그 생성이 완료되었습니다."),
    POST_CREATE_SUCCESS(HttpStatus.CREATED.value(),"게시글 생성이 완료되었습니다."),

    POST_FIND_SUCCESS(HttpStatus.OK.value(), "게시글 찾기가 완료되었습니다."),

    TOKEN_REISSUE_SUCCESS(HttpStatus.OK.value(), "액세스 토큰 재발급이 완료되었습니다."),
    MEMBER_LOGIN_SUCCESS(HttpStatus.OK.value(), "로그인이 성공적으로 완료되었습니다.")
    ;

    private final int status;
    private final String message;
}
