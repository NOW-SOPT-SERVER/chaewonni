package org.sopt.daangnMarket.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.daangnMarket.common.auth.PrincipalHandler;
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

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ItemController {

    private final ItemService itemService;
    private final PrincipalHandler principalHandler;

    //물건 등록
    @PostMapping("/items")
    public ResponseEntity<ApiResponse<Void>> createItem(
            @Validated @ModelAttribute ItemCreateDto itemCreate) {
        itemService.createItem(principalHandler.getUserIdFromPrincipal(), itemCreate);
        return ApiUtils.success(HttpStatus.CREATED, SuccessMessage.ITEM_CREATE_SUCCESS);
    }

    // 등록된 상품들에 대한 지역별 조회
    @GetMapping("/items/location/{locationId}")
    public ResponseEntity<ApiResponse<List<ItemFindAllDto>>> findAllItems(
            @PathVariable(name = "locationId") Long locationId
    ) {
        return ApiUtils.success(HttpStatus.OK, SuccessMessage.ITEMS_FIND_SUCCESS, itemService.findAllItems(locationId));
    }

    //등록한 상품 지우기
    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<ApiResponse<Void>> deleteItem(
            @PathVariable(name = "itemId") Long itemId
    ) {
        itemService.deleteItem(principalHandler.getUserIdFromPrincipal(), itemId);
        return ApiUtils.success(HttpStatus.OK, SuccessMessage.ITEMS_DELETE_SUCCESS);
    }
}
