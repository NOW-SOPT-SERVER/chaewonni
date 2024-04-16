package org.sopt.daangnMarket.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.daangnMarket.dto.Item.request.ItemCreateDto;
import org.sopt.daangnMarket.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ItemService itemService;

    //물건 등록
    @PostMapping
    public ResponseEntity<Void> createItem(
            @RequestBody ItemCreateDto itemCreate) {
        return ResponseEntity.created(URI.create(itemService.createItem(itemCreate))).build();
    }


}
