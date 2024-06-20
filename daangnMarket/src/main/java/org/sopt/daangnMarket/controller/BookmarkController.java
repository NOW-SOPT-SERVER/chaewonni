package org.sopt.daangnMarket.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.sopt.daangnMarket.common.auth.PrincipalHandler;
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
    private final PrincipalHandler principalHandler;

    @PostMapping("/bookmarks/{itemId}")
    public ResponseEntity<ApiResponse<Void>> addBookmark(@PathVariable @NotNull Long itemId) {
        bookmarkService.addBookmark(principalHandler.getUserIdFromPrincipal(), itemId);
        return ApiUtils.success(HttpStatus.CREATED, SuccessMessage.BOOKMARK_CREATE_SUCCESS);
    }
}
