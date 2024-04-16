package org.sopt.daangnMarket.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.sopt.daangnMarket.domain.Member;
import org.sopt.daangnMarket.dto.Member.request.MemberCreateDto;
import org.sopt.daangnMarket.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public String createMember(MemberCreateDto memberCreate) {

        Member member = Member.create(memberCreate.nickname(), memberCreate.phoneNumber());
        memberRepository.save(member);
        return member.getId().toString();
    }
}
