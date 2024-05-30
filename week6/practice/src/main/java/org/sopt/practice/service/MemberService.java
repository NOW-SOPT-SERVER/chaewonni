package org.sopt.practice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.sopt.practice.common.auth.redis.domain.Token;
import org.sopt.practice.common.auth.redis.repository.TokenRepository;
import org.sopt.practice.common.jwt.JwtTokenProvider;
import org.sopt.practice.common.auth.UserAuthentication;
import org.sopt.practice.domain.Member;
import org.sopt.practice.repository.MemberRepository;
import org.sopt.practice.service.dto.MemberCreateDto;
import org.sopt.practice.service.dto.MemberFindDto;
import org.sopt.practice.service.dto.UserJoinResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenRepository tokenRepository;

    @Transactional
    public UserJoinResponse createMember(
            MemberCreateDto memberCreate
    ) {
        Member member = memberRepository.save(
                Member.create(memberCreate.name(), memberCreate.part(), memberCreate.age())
        );
        Long memberId = member.getId();
        UserAuthentication userAuthentication = UserAuthentication.createUserAuthentication(memberId);
        String accessToken = jwtTokenProvider.issueAccessToken(
                userAuthentication
        );
        String refreshToken = jwtTokenProvider.issueRefreshToken(
                userAuthentication
        );

        tokenRepository.save(Token.of(memberId, refreshToken));

        return UserJoinResponse.of(accessToken, refreshToken, memberId.toString());
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
