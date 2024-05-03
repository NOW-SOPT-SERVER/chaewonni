package org.sopt.daangnMarket.dto.response.item;

import org.sopt.daangnMarket.domain.Item;
import org.sopt.daangnMarket.dto.response.location.LocationFindDto;
import org.sopt.daangnMarket.dto.response.member.MemberFindDto;

public record ItemFindDto(

        Long id,
        String title,
        String transactionType,
        int price,
        String description,
        LocationFindDto registeredLocation,
        String address,
        String saleStatus,
        String category,
        MemberFindDto member
) {
    public static ItemFindDto of(Item item) {
        return new ItemFindDto(
                item.getId(),
                item.getTitle(),
                item.getTradeType().getKoreanName(),
                item.getPrice(),
                item.getDescription(),
                LocationFindDto.of(item.getRegisteredLocation()),
                item.getAddress(),
                item.getSaleStatus().getKoreanName(),
                item.getCategory().getKoreanName(),
                MemberFindDto.of(item.getMember()));
    }
}
