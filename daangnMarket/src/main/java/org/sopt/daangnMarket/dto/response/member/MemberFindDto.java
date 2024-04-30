package org.sopt.daangnMarket.dto.response.member;

import org.sopt.daangnMarket.domain.Location;
import org.sopt.daangnMarket.domain.Member;

public record MemberFindDto(

        Long id,
        String nickname,
        Location location,
        double mannerTemperature
) {
    public static MemberFindDto of(Member member) {
        return new MemberFindDto(
                member.getId(),
                member.getNickname(),
                member.getLocation(),
                member.getMannerTemperature());
    }
}
