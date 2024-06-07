package org.sopt.daangnMarket.common.auth.service;

import lombok.RequiredArgsConstructor;
import org.sopt.daangnMarket.common.auth.UserAuthentication;
import org.sopt.daangnMarket.common.auth.dto.CustomUserDetails;
import org.sopt.daangnMarket.common.auth.redis.domain.Token;
import org.sopt.daangnMarket.common.auth.redis.repository.TokenRepository;
import org.sopt.daangnMarket.common.jwt.JwtTokenProvider;
import org.sopt.daangnMarket.dto.response.member.MemberJoinResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtTokenProvider jwtTokenProvider;
    private final TokenRepository tokenRepository;

    public MemberJoinResponse login(CustomUserDetails userDetails) {
        Long memberId = userDetails.getMemberId();  // memberId 추출

        String accessToken = jwtTokenProvider.issueAccessToken(userDetails);
        String refreshToken = jwtTokenProvider.issueRefreshToken(userDetails);
        tokenRepository.save(Token.of(memberId, refreshToken));

        return MemberJoinResponse.of(accessToken, refreshToken, memberId.toString());
    }
}
