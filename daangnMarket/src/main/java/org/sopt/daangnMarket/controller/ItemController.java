package org.sopt.daangnMarket.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.daangnMarket.dto.request.item.ItemCreateDto;
import org.sopt.daangnMarket.dto.response.item.ItemFindAllDto;
import org.sopt.daangnMarket.service.ItemService;
import org.sopt.daangnMarket.util.ApiResponse;
import org.sopt.daangnMarket.util.ApiUtils;
import org.sopt.daangnMarket.util.dto.SuccessMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ItemController {

    private final ItemService itemService;

    //물건 등록
    @PostMapping("/items")
    public ResponseEntity<ApiResponse<Void>> createItem(
            @Validated @RequestBody ItemCreateDto itemCreate) {
        itemService.createItem(itemCreate);
        return ApiUtils.success(HttpStatus.CREATED, SuccessMessage.ITEM_CREATE_SUCCESS);
    }

    // 등록된 상품들에 대한 지역별 조회
    @GetMapping("/items/location/{locationId}")
    public ResponseEntity<ApiResponse<List<ItemFindAllDto>>> findAllItems(
            @PathVariable Long locationId
    ) {
        return ApiUtils.success(HttpStatus.OK, SuccessMessage.ITEMS_FIND_SUCCESS, itemService.findAllItems(locationId));
    }
}
