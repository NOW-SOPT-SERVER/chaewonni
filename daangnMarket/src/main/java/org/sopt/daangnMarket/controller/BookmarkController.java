package org.sopt.daangnMarket.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.daangnMarket.service.BookmarkService;
import org.sopt.daangnMarket.util.ApiResponse;
import org.sopt.daangnMarket.util.ApiUtils;
import org.sopt.daangnMarket.util.dto.SuccessMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping("/bookmarks/{itemId}")
    public ResponseEntity<ApiResponse<Void>> addBookmark(@PathVariable Long itemId, @RequestParam Long memberId) {
        bookmarkService.addBookmark(memberId, itemId);
        return ApiUtils.success(HttpStatus.CREATED, SuccessMessage.BOOKMARK_CREATE_SUCCESS);
    }
}
