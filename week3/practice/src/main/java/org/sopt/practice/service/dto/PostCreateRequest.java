package org.sopt.practice.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostCreateRequest(
        @NotBlank(message = "게시글 제목은 필수로 입력해야 합니다.")
        @Size(max = 100, message = "게시글 제목이 최대 글자 수(100자)를 초과했습니다.")
        String title,

        @NotBlank(message = "게시글 내용은 필수로 입력해야 합니다.")
        String content
) {
}
