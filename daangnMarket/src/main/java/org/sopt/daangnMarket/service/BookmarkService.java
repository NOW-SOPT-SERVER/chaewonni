package org.sopt.daangnMarket.service;

import lombok.RequiredArgsConstructor;
import org.sopt.daangnMarket.domain.Bookmark;
import org.sopt.daangnMarket.domain.Item;
import org.sopt.daangnMarket.domain.Member;
import org.sopt.daangnMarket.exception.ApiErrorCode;
import org.sopt.daangnMarket.exception.ConflictException;
import org.sopt.daangnMarket.exception.NotFoundException;
import org.sopt.daangnMarket.repository.BookmarkRepository;
import org.sopt.daangnMarket.repository.ItemRepository;
import org.sopt.daangnMarket.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final BookmarkRepository bookmarkRepository;

    @Transactional
    public void addBookmark(Long memberId, Long itemId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ApiErrorCode.MEMBER_NOT_FOUND));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException(ApiErrorCode.ITEM_NOT_FOUND));

        bookmarkRepository.findByMemberAndItem(member, item)
                .ifPresent(b -> {
                    throw new ConflictException(ApiErrorCode.BOOKMARK_CONFLICT);
                });

        item.setBookmarkCount(item.getBookmarkCount() + 1);
        bookmarkRepository.save(Bookmark.create(member, item));
    }
}
