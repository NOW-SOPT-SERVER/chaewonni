package org.sopt.daangnMarket.dto.response.item;

import org.sopt.daangnMarket.domain.Item;
import org.sopt.daangnMarket.domain.enums.TransactionType;
import org.sopt.daangnMarket.domain.enums.SaleStatus;
import org.sopt.daangnMarket.dto.response.member.MemberFindDto;

public record ItemFindDto(
        Long id,
        String title,
        TransactionType transactionType,
        int price,
        String description,
        String address,
        SaleStatus saleStatus,
        String category,
        MemberFindDto member
) {
    public static ItemFindDto of(Item item) {
        return new ItemFindDto(
                item.getId(),
                item.getTitle(),
                item.getTransactionType(),
                item.getPrice(),
                item.getDescription(),
                item.getAddress(),
                item.getSaleStatus(),
                item.getCategory().getName(),
                MemberFindDto.of(item.getMember()));
    }
}
