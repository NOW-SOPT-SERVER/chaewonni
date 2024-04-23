package org.sopt.daangnMarket.dto.response.item;

import org.sopt.daangnMarket.domain.Item;
import org.sopt.daangnMarket.domain.enums.Category;
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
        Category category,
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
                item.getCategory(),
                MemberFindDto.of(item.getMember()));
    }
}
