package org.sopt.daangnMarket.dto.request.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MemberCreateDto(

        @NotBlank(message = "닉네임은 필수 입력 사항입니다.")
        @Size(min = 2, max = 10, message = "닉네임은 2글자 이상, 10글자 이하여야 합니다.")
        String nickname,
        @NotBlank(message = "핸드폰 번호는 필수 입력 사항입니다.")
        String phoneNumber,
        @NotBlank(message = "사용자 동네 인증은 필수 사항입니다.")
        String location
) {
}
