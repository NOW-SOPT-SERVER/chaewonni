package org.sopt.daangnMarket.repository;

import org.sopt.daangnMarket.domain.Item;
import org.sopt.daangnMarket.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {

//    List<Item> findAllByRegisteredLocation(Location location);
}
