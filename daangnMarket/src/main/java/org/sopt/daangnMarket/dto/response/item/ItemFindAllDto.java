package org.sopt.daangnMarket.dto.response.item;

import org.sopt.daangnMarket.domain.Item;
import org.sopt.daangnMarket.domain.Location;
import org.sopt.daangnMarket.domain.enums.Category;
import org.sopt.daangnMarket.domain.enums.SaleStatus;
import org.sopt.daangnMarket.domain.enums.TransactionType;
import org.sopt.daangnMarket.dto.response.location.LocationFindDto;

import java.util.List;

public record ItemFindAllDto(

        Long id,
        String title,
        String transactionType,
        int price,
        LocationFindDto registeredLocation,
        String saleStatus
) {
    public static List<ItemFindAllDto> listOf(List<Item> items) {
        return items
                .stream()
                .map(item -> new ItemFindAllDto(
                        item.getId(),
                        item.getTitle(),
                        item.getTransactionType().getKoreanName(),
                        item.getPrice(),
                        LocationFindDto.of(item.getRegisteredLocation()),
                        item.getSaleStatus().getKoreanName()
                )).toList();
    }
}
