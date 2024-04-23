package org.sopt.daangnMarket.dto.request.item;

import org.sopt.daangnMarket.domain.enums.TransactionType;

public record ItemCreateDto(

        Long memberId,
        String title,
        String category,
        TransactionType transactionType,
        int price,
        String description,
        String address
) {
}
