package org.sopt.practice.service;

import lombok.RequiredArgsConstructor;
import org.sopt.practice.common.auth.redis.repository.TokenRepository;
import org.sopt.practice.common.dto.ErrorMessage;
import org.sopt.practice.common.jwt.JwtTokenProvider;
import org.sopt.practice.domain.Member;
import org.sopt.practice.domain.Role;
import org.sopt.practice.exception.DuplicateMemberException;
import org.sopt.practice.repository.MemberRepository;
import org.sopt.practice.service.dto.MemberCreateDto;
import org.sopt.practice.service.dto.MemberFindDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenRepository tokenRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void createMember(MemberCreateDto memberCreate) {
        validateDuplicateMember(memberCreate);

        Member member = Member.builder()
                .username(memberCreate.username())
                .password(bCryptPasswordEncoder.encode(memberCreate.password()))
                .role(Role.ROLE_USER)
                .name(memberCreate.name())
                .part(memberCreate.part())
                .age(memberCreate.age())
                .build();

        memberRepository.save(member);
    }

    private void validateDuplicateMember(MemberCreateDto memberCreate) {
        // 아이디(username) 중복 검사
        boolean existsByUsername = memberRepository.existsByUsername(memberCreate.username());
        if (existsByUsername) {
            throw new DuplicateMemberException(ErrorMessage.DUPLICATE_USERNAME);
        }

    }

    public Member findById(Long memberId) {
        return memberRepository.findByIdOrThrow(memberId);
    }

    public MemberFindDto findMemberById(Long memberId) {
        return MemberFindDto.of(memberRepository.findByIdOrThrow(memberId));
    }

    @Transactional
    public void deleteMemberById(Long memberId) {
        memberRepository.delete(memberRepository.findByIdOrThrow(memberId));
    }

    public List<MemberFindDto> findAllMembers() {
        return memberRepository.findAll().stream()
                .map(MemberFindDto::of)
                .collect(Collectors.toList());
    }
}
