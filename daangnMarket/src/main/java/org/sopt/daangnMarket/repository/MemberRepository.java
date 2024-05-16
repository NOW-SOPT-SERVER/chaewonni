package org.sopt.daangnMarket.repository;

import org.sopt.daangnMarket.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

//    Long countByPhoneNumber(String phoneNumber);
}
