package org.sopt.daangnMarket.dto.Item.response;

import org.sopt.daangnMarket.domain.Item;
import org.sopt.daangnMarket.domain.Member;
import org.sopt.daangnMarket.domain.enums.Method;
import org.sopt.daangnMarket.domain.enums.SaleStatus;
import org.sopt.daangnMarket.dto.Member.response.MemberFindDto;

public record ItemFindDto(
        Long id,
        String title,
        Method method,
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
                item.getMethod(),
                item.getPrice(),
                item.getDescription(),
                item.getAddress(),
                item.getSaleStatus(),
                item.getCategory().getName(),
                MemberFindDto.of(item.getMember()));
    }
}
