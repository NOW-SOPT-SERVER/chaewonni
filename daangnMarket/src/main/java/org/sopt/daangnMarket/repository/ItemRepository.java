package org.sopt.daangnMarket.repository;

import org.sopt.daangnMarket.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
