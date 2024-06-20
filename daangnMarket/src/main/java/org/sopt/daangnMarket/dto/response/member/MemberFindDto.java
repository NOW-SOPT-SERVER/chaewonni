package org.sopt.daangnMarket.dto.response.member;

import org.sopt.daangnMarket.domain.Location;
import org.sopt.daangnMarket.domain.Member;
import org.sopt.daangnMarket.dto.response.location.LocationFindDto;

public record MemberFindDto(

        Long id,
        String username,
        String nickname,
        LocationFindDto location,
        double mannerTemperature
) {
    public static MemberFindDto of(Member member) {
        return new MemberFindDto(
                member.getId(),
                member.getUsername(),
                member.getNickname(),
                LocationFindDto.of(member.getLocation()),
                member.getMannerTemperature());
    }
}
