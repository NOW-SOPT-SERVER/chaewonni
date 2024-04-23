package org.sopt.practice.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.sopt.practice.common.dto.ErrorMessage;
import org.sopt.practice.domain.Member;
import org.sopt.practice.exception.NotFoundException;
import org.sopt.practice.repository.MemberRepository;
import org.sopt.practice.service.dto.MemberCreateDto;
import org.sopt.practice.service.dto.MemberFindDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional //db에 변경사항이 생길 때 사용
    public String createMember(MemberCreateDto memberCreate) {

        Member member = Member.create(memberCreate.name(), memberCreate.part(), memberCreate.age());
        memberRepository.save(member);
        return member.getId().toString();
    }

//    public Member findById(Long memberId) {
//        return memberRepository.findByIdOrThrow(memberId);
//    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND)
        );
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
