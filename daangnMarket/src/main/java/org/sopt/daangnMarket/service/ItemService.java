package org.sopt.daangnMarket.service;

import lombok.RequiredArgsConstructor;
import org.sopt.daangnMarket.domain.Item;
import org.sopt.daangnMarket.domain.Location;
import org.sopt.daangnMarket.domain.Member;
import org.sopt.daangnMarket.domain.enums.Category;
import org.sopt.daangnMarket.domain.enums.SaleStatus;
import org.sopt.daangnMarket.domain.enums.TradeType;
import org.sopt.daangnMarket.dto.request.item.ItemCreateDto;
import org.sopt.daangnMarket.dto.response.item.ItemFindAllDto;
import org.sopt.daangnMarket.util.dto.ErrorMessage;
import org.sopt.daangnMarket.exception.NotFoundException;
import org.sopt.daangnMarket.repository.ItemRepository;
import org.sopt.daangnMarket.repository.LocationRepository;
import org.sopt.daangnMarket.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final LocationRepository locationRepository;

    @Transactional
    public void createItem(ItemCreateDto itemCreate) {
        Member member = memberRepository.findById(itemCreate.memberId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND));

        Category category = Category.fromKoreanName(itemCreate.category());

        TradeType tradeType = TradeType.fromKoreanName(itemCreate.transactionType());

        Item item = Item.builder()
                .title(itemCreate.title())
                .tradeType(tradeType)
                .price(itemCreate.price())
                .description(itemCreate.description())
                .registeredLocation(member.getLocation())
                .address(itemCreate.address())
                .saleStatus(SaleStatus.FOR_SALE)
                .category(category)
                .member(member)
                .build();

        itemRepository.save(item);
    }

    @Transactional(readOnly = true)
    // 등록된 상품들에 대한 지역별 조회
    public List<ItemFindAllDto> findAllItems(Long locationId) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.LOCATION_NOT_FOUND));

        return ItemFindAllDto.listOf(itemRepository.findAllByRegisteredLocation(location));
    }


}
