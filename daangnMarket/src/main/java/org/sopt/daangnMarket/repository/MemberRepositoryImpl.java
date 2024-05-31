//package org.sopt.daangnMarket.repository;
//
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import jakarta.persistence.EntityManager;
//
//import static org.sopt.daangnMarket.domain.QMember.*;
//
//public class MemberRepositoryImpl implements MemberRepositoryCustom {
//    private final JPAQueryFactory queryFactory;
//
//    public MemberRepositoryImpl(EntityManager em) {
//        this.queryFactory = new JPAQueryFactory(em);
//    }
//
//    @Override
//    public Long countByPhoneNumber(String phoneNumber) {
//        return queryFactory
//                .select(member.count())
//                .from(member)
//                .where(member.phoneNumber.eq(phoneNumber))
//                .fetchOne();
//    }
//
//}
