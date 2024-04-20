package org.sopt.practice.service.dto;

import org.sopt.practice.domain.Member;
import org.sopt.practice.domain.Part;

public record MemberFindDto(
        Long id,
        String name,
        Part part,
        int age
) {

    public static MemberFindDto of(Member member) {
        return new MemberFindDto(
                member.getId(),
                member.getName(),
                member.getPart(),
                member.getAge());
    }
}
