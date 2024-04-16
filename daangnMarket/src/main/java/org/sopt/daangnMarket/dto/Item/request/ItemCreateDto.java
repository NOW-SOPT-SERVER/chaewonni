package org.sopt.daangnMarket.dto.Item.request;

import org.sopt.daangnMarket.domain.Category;
import org.sopt.daangnMarket.domain.enums.Method;

public record ItemCreateDto(
        Long memberId,
        String title,
        String category,
        Method method,
        int price,
        String description,
        String address
) {
}
