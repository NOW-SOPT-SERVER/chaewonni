package org.sopt.practice.common.auth.service;

import lombok.RequiredArgsConstructor;
import org.sopt.practice.common.auth.dto.CustomUserDetails;
import org.sopt.practice.common.auth.redis.domain.Token;
import org.sopt.practice.common.auth.redis.repository.TokenRepository;
import org.sopt.practice.common.jwt.JwtTokenProvider;
import org.sopt.practice.service.dto.UserJoinResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtTokenProvider jwtTokenProvider;
    private final TokenRepository tokenRepository;

    public UserJoinResponse handleLoginSuccess(CustomUserDetails userDetails) {
        Long memberId = userDetails.getMemberId();  // memberId 추출

        String accessToken = jwtTokenProvider.issueAccessToken(userDetails);
        String refreshToken = jwtTokenProvider.issueRefreshToken(userDetails);
        tokenRepository.save(Token.of(memberId, refreshToken));

        return UserJoinResponse.of(accessToken, refreshToken, memberId.toString());
    }
}
