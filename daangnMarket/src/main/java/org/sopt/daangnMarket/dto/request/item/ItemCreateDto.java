package org.sopt.daangnMarket.dto.request.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.sopt.daangnMarket.domain.enums.Category;
import org.sopt.daangnMarket.domain.enums.TransactionType;

public record ItemCreateDto(

        Long memberId,
        @NotBlank(message = "제목은 필수 입력 사항입니다.")
        String title,
        @NotBlank(message = "카테고리는 필수 선택 사항입니다.")
        String category,
        String transactionType,
        int price,
        @NotBlank(message = "자세한 설명은 필수 입력 사항입니다.")
        @Size(min = 4, message = "최소 4글자 이상이어야 합니다.")
        String description,
        String address
) {
}