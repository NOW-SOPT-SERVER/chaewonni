package org.sopt.daangnMarket.dto.request.item;

import org.sopt.daangnMarket.domain.enums.Category;
import org.sopt.daangnMarket.domain.enums.TransactionType;

public record ItemCreateDto(

        Long memberId,
        String title,
        String category,
        String transactionType,
        int price,
        String description,
        String address
) {
}
