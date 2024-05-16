package org.sopt.daangnMarket.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.sopt.daangnMarket.domain.Item;
import org.sopt.daangnMarket.domain.Location;
import org.sopt.daangnMarket.domain.QLocation;

import static org.sopt.daangnMarket.domain.QItem.*;

import java.util.List;

public class ItemRepositoryImpl implements ItemRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ItemRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Item> findAllByRegisteredLocation(Location location) {
        return queryFactory
                .selectFrom(item)
                .join(item.registeredLocation, QLocation.location)
                .where(QLocation.location.eq(location))
                .fetch();
    }
}
