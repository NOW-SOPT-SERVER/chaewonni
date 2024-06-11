package org.sopt.daangnMarket.dto.request.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record ItemCreateDto(

        @NotBlank(message = "제목은 필수 입력 사항입니다.")
        String title,
        @NotBlank(message = "카테고리는 필수 선택 사항입니다.")
        String category,
        String tradeType,
        int price,
        @Size(min = 4, message = "설명란은 최소 4글자 이상이어야 합니다.")
        String description,
        String address,
        MultipartFile image
) {
}
