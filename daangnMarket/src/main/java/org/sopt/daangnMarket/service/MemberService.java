package org.sopt.daangnMarket.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.sopt.daangnMarket.domain.Member;
import org.sopt.daangnMarket.dto.request.member.MemberCreateDto;
import org.sopt.daangnMarket.dto.response.member.MemberFindDto;
import org.sopt.daangnMarket.exception.ApiErrorCode;
import org.sopt.daangnMarket.exception.DuplicateMemberException;
import org.sopt.daangnMarket.exception.NotFoundException;
import org.sopt.daangnMarket.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void createMember(MemberCreateDto memberCreate) {
        validateDuplicateMember(memberCreate);
        Member member = Member.create(memberCreate.nickname(), memberCreate.phoneNumber());
        memberRepository.save(member);
    }

    private void validateDuplicateMember(MemberCreateDto memberCreate) {
        Long count = memberRepository.countByPhoneNumber(memberCreate.phoneNumber());
        if (count > 0) {
            // 중복된 이메일이나 학번이 존재하는 경우 예외 발생
            throw new DuplicateMemberException(ApiErrorCode.DUPLICATE_MEMBER);
        }
    }

    public MemberFindDto findMember(Long memberId) {
       Member member = memberRepository.findById(memberId).orElseThrow(
               () -> new NotFoundException(ApiErrorCode.MEMBER_NOT_FOUND)
       );
       return MemberFindDto.of(member);
    }
}