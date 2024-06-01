package org.sopt.daangnMarket.service;

import lombok.RequiredArgsConstructor;
import org.sopt.daangnMarket.domain.Location;
import org.sopt.daangnMarket.domain.Member;
import org.sopt.daangnMarket.domain.enums.Role;
import org.sopt.daangnMarket.dto.request.member.MemberCreateDto;
import org.sopt.daangnMarket.dto.response.member.MemberFindDto;
import org.sopt.daangnMarket.util.dto.ErrorMessage;
import org.sopt.daangnMarket.exception.DuplicateMemberException;
import org.sopt.daangnMarket.exception.NotFoundException;
import org.sopt.daangnMarket.repository.LocationRepository;
import org.sopt.daangnMarket.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final LocationService locationService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public static final double DEFAULT_MANNER_TEMPERATURE = 36.5;

    @Transactional
    public void createMember(MemberCreateDto memberCreate) {
        validateDuplicateMember(memberCreate);
        Location location = locationService.getLocationByStreet(memberCreate.location());

        Member member = Member.builder()
                .username(memberCreate.username())
                .password(bCryptPasswordEncoder.encode(memberCreate.password()))
                .role(Role.ROLE_USER)
                .nickname(memberCreate.nickname())
                .phoneNumber(memberCreate.phoneNumber())
                .mannerTemperature(DEFAULT_MANNER_TEMPERATURE)
                .location(location)
                .build();

        memberRepository.save(member);
    }

    private void validateDuplicateMember(MemberCreateDto memberCreate) {
        // 아이디(username) 중복 검사
        boolean existsByUsername = memberRepository.existsByUsername(memberCreate.username());
        if (existsByUsername) {
            throw new DuplicateMemberException(ErrorMessage.DUPLICATE_USERNAME);
        }

        // 전화번호(phoneNumber) 중복 검사
        boolean existsByPhoneNumber = memberRepository.existsByPhoneNumber(memberCreate.phoneNumber());
        if (existsByPhoneNumber) {
            throw new DuplicateMemberException(ErrorMessage.DUPLICATE_PHONE);
        }
    }

    @Transactional(readOnly = true)
    public MemberFindDto findMember(Long memberId) {
       Member member = getMemberById(memberId);
       return MemberFindDto.of(member);
    }

    public Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND));
    }
}
