package org.sopt.daangnMarket.service;

import lombok.RequiredArgsConstructor;
import org.sopt.daangnMarket.domain.Item;
import org.sopt.daangnMarket.domain.Member;
import org.sopt.daangnMarket.domain.enums.Category;
import org.sopt.daangnMarket.domain.enums.SaleStatus;
import org.sopt.daangnMarket.domain.enums.TransactionType;
import org.sopt.daangnMarket.dto.request.item.ItemCreateDto;
import org.sopt.daangnMarket.exception.ApiErrorCode;
import org.sopt.daangnMarket.exception.NotFoundException;
import org.sopt.daangnMarket.repository.ItemRepository;
import org.sopt.daangnMarket.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    public void createItem(ItemCreateDto itemCreate) {
        Member member = memberRepository.findById(itemCreate.memberId())
                .orElseThrow(() -> new NotFoundException(ApiErrorCode.MEMBER_NOT_FOUND));

        Category category = Category.fromKoreanName(itemCreate.category());

        TransactionType transactionType = TransactionType.fromKoreanName(itemCreate.transactionType());

        Item item = Item.builder()
                .title(itemCreate.title())
                .transactionType(transactionType)
                .price(itemCreate.price())
                .description(itemCreate.description())
                .address(itemCreate.address())
                .saleStatus(SaleStatus.FOR_SALE)
                .category(category)
                .member(member)
                .build();

        itemRepository.save(item);
    }
}
