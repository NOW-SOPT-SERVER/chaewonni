package org.sopt.daangnMarket.service;

import lombok.RequiredArgsConstructor;
import org.sopt.daangnMarket.common.auth.PrincipalHandler;
import org.sopt.daangnMarket.domain.Item;
import org.sopt.daangnMarket.domain.Location;
import org.sopt.daangnMarket.domain.Member;
import org.sopt.daangnMarket.domain.enums.Category;
import org.sopt.daangnMarket.domain.enums.SaleStatus;
import org.sopt.daangnMarket.domain.enums.TradeType;
import org.sopt.daangnMarket.dto.request.item.ItemCreateDto;
import org.sopt.daangnMarket.dto.response.item.ItemFindAllDto;
import org.sopt.daangnMarket.exception.ForbiddenException;
import org.sopt.daangnMarket.exception.UnauthorizedException;
import org.sopt.daangnMarket.external.S3Service;
import org.sopt.daangnMarket.util.dto.ErrorMessage;
import org.sopt.daangnMarket.exception.NotFoundException;
import org.sopt.daangnMarket.repository.ItemRepository;
import org.sopt.daangnMarket.repository.LocationRepository;
import org.sopt.daangnMarket.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private final MemberService memberService;
    private final LocationService locationService;

    private final S3Service s3Service;
    private static final String ITEM_S3_UPLOAD_FOLDER = "item/";

    @Transactional
    public void createItem(Long memberId, ItemCreateDto itemCreate) {
        Member member = memberService.getMemberById(memberId);

        Category category = Category.fromKoreanName(itemCreate.category());

        TradeType tradeType = TradeType.fromKoreanName(itemCreate.tradeType());

        try{
            Item item = Item.builder()
                    .title(itemCreate.title())
                    .tradeType(tradeType)
                    .price(itemCreate.price())
                    .description(itemCreate.description())
                    .registeredLocation(member.getLocation())
                    .address(itemCreate.address())
                    .saleStatus(SaleStatus.FOR_SALE)
                    .category(category)
                    .imageUrl(s3Service.uploadImage(ITEM_S3_UPLOAD_FOLDER, itemCreate.image()))
                    .member(member)
                    .build();

            itemRepository.save(item);
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    // 등록된 상품들에 대한 지역별 조회
    public List<ItemFindAllDto> findAllItems(Long locationId) {
        Location location = locationService.getLocationById(locationId);

        return ItemFindAllDto.listOf(getAllItemByRegisteredLocation(location));
    }

    @Transactional
    public void deleteItem(Long memberId, Long itemId) {
        Item item = getItemById(itemId);

        if(!memberId.equals(item.getMember().getId())) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN_MEMBER_ACCESS);
        }

        try{
            s3Service.deleteImage(item.getImageUrl());
            itemRepository.delete(item);
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.ITEM_NOT_FOUND));
    }

    public List<Item> getAllItemByRegisteredLocation(Location location) {
        return itemRepository.findAllByRegisteredLocation(location);
    }
}
