package org.sopt.daangnMarket.service;

import lombok.RequiredArgsConstructor;
import org.sopt.daangnMarket.domain.Location;
import org.sopt.daangnMarket.domain.Member;
import org.sopt.daangnMarket.dto.request.member.MemberCreateDto;
import org.sopt.daangnMarket.dto.response.member.MemberFindDto;
import org.sopt.daangnMarket.util.dto.ErrorMessage;
import org.sopt.daangnMarket.exception.DuplicateMemberException;
import org.sopt.daangnMarket.exception.NotFoundException;
import org.sopt.daangnMarket.repository.LocationRepository;
import org.sopt.daangnMarket.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final LocationRepository locationRepository;

    @Transactional
    public void createMember(MemberCreateDto memberCreate) {
        validateDuplicateMember(memberCreate);
        Location location = locationRepository.findByStreet(memberCreate.location())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.LOCATION_NOT_FOUND));
        Member member = Member.create(memberCreate.nickname(), memberCreate.phoneNumber(), location);
        memberRepository.save(member);
    }

    private void validateDuplicateMember(MemberCreateDto memberCreate) {
        Long count = memberRepository.countByPhoneNumber(memberCreate.phoneNumber());
        if (count > 0) {
            // 중복된 이메일이나 학번이 존재하는 경우 예외 발생
            throw new DuplicateMemberException(ErrorMessage.DUPLICATE_MEMBER);
        }
    }

    @Transactional(readOnly = true)
    public MemberFindDto findMember(Long memberId) {
       Member member = memberRepository.findById(memberId).orElseThrow(
               () -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND)
       );
       return MemberFindDto.of(member);
    }
}
