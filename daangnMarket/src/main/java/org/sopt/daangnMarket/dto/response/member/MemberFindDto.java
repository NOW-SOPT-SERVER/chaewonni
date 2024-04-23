package org.sopt.daangnMarket.dto.response.member;

import org.sopt.daangnMarket.domain.Member;

public record MemberFindDto(
        Long id,
        String nickname,
        double mannerTemperature
) {
    public static MemberFindDto of(Member member) {
        return new MemberFindDto(
                member.getId(),
                member.getNickname(),
                member.getMannerTemperature());
    }
}