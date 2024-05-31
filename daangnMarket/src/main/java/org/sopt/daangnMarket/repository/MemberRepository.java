package org.sopt.daangnMarket.repository;

import org.sopt.daangnMarket.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUsername(String username);

    Long countByPhoneNumber(String phoneNumber);
}
