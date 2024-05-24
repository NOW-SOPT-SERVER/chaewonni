package org.sopt.practice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

//    @Transactional //db에 변경사항이 생길 때 사용
//    public String createMember(MemberCreateDto memberCreate) {
//
//        Member member = Member.create(memberCreate.name(), memberCreate.part(), memberCreate.age());
//        memberRepository.save(member);
//        return member.getId().toString();
//    }

    @Transactional
    public UserJoinResponse createMember(
            MemberCreateDto memberCreate
    ) {
        Member member = memberRepository.save(
                Member.create(memberCreate.name(), memberCreate.part(), memberCreate.age())
        );
        Long memberId = member.getId();
        String accessToken = jwtTokenProvider.issueAccessToken(
                UserAuthentication.createUserAuthentication(memberId)
        );
        return UserJoinResponse.of(accessToken, memberId.toString());
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
