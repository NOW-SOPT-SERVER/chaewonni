package org.sopt.daangnMarket.dto.response.item;

import org.sopt.daangnMarket.domain.Item;
import org.sopt.daangnMarket.domain.Location;
import org.sopt.daangnMarket.domain.enums.Category;
import org.sopt.daangnMarket.domain.enums.TransactionType;
import org.sopt.daangnMarket.domain.enums.SaleStatus;
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
                item.getTransactionType().getKoreanName(),
                item.getPrice(),
                item.getDescription(),
                LocationFindDto.of(item.getRegisteredLocation()),
                item.getAddress(),
                item.getSaleStatus().getKoreanName(),
                item.getCategory().getKoreanName(),
                MemberFindDto.of(item.getMember()));
    }
}
