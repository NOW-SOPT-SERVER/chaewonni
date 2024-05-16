package org.sopt.daangnMarket.repository;

import org.sopt.daangnMarket.domain.Item;
import org.sopt.daangnMarket.domain.Location;

import java.util.List;

public interface ItemRepositoryCustom {

    List<Item> findAllByRegisteredLocation(Location location);
}
