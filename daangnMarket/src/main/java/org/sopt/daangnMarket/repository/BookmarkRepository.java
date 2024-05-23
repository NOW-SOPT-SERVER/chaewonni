package org.sopt.daangnMarket.repository;

import org.sopt.daangnMarket.domain.Bookmark;
import org.sopt.daangnMarket.domain.Item;
import org.sopt.daangnMarket.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {

    Optional<Bookmark> findByMemberAndItem(Member member, Item item);
}
