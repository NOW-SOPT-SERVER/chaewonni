package org.sopt.daangnMarket.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.daangnMarket.dto.request.item.ItemCreateDto;
import org.sopt.daangnMarket.service.ItemService;
import org.sopt.daangnMarket.util.ApiResponse;
import org.sopt.daangnMarket.util.ApiUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ItemService itemService;

    //물건 등록
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createItem(
            @Validated @RequestBody ItemCreateDto itemCreate) {
        itemService.createItem(itemCreate);
        return ApiUtils.success(HttpStatus.CREATED);
    }


}
