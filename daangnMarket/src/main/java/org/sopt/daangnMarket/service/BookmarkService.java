package org.sopt.daangnMarket.service;

import lombok.RequiredArgsConstructor;
import org.sopt.daangnMarket.domain.Bookmark;
import org.sopt.daangnMarket.domain.Item;
import org.sopt.daangnMarket.domain.Member;
import org.sopt.daangnMarket.util.dto.ErrorMessage;
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

    private final BookmarkRepository bookmarkRepository;

    private final MemberService memberService;
    private final ItemService itemService;

    //좋아요 추가
    @Transactional
    public void addBookmark(Long memberId, Long itemId) {
        Member member = memberService.getMemberById(memberId);

        Item item = itemService.getItemById(itemId);

        bookmarkRepository.findByMemberAndItem(member, item)
                .ifPresent(b -> {
                    throw new ConflictException(ErrorMessage.BOOKMARK_CONFLICT);
                });

        item.addBookmarkCount();
        bookmarkRepository.save(Bookmark.create(member, item));
    }
}
