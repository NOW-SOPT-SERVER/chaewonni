package org.sopt.daangnMarket.dto.response.member;

public record MemberJoinResponse(
        String accessToken,
        String refreshToken,
        String userId
) {

    public static MemberJoinResponse of(
            String accessToken,
            String refreshToken,
            String userId
    ) {
        return new MemberJoinResponse(accessToken, refreshToken, userId);
    }
}
