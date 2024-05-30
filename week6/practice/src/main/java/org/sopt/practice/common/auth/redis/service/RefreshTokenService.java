package org.sopt.practice.common.auth.redis.service;

import lombok.RequiredArgsConstructor;
import org.sopt.practice.common.auth.UserAuthentication;
import org.sopt.practice.common.auth.redis.domain.Token;
import org.sopt.practice.common.auth.redis.repository.TokenRepository;
import org.sopt.practice.common.dto.ErrorMessage;
import org.sopt.practice.common.jwt.JwtTokenProvider;
import org.sopt.practice.common.jwt.JwtValidationType;
import org.sopt.practice.exception.NotFoundException;
import org.sopt.practice.exception.UnauthorizedException;
import org.sopt.practice.service.dto.ReissueTokenResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final TokenRepository tokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public ReissueTokenResponse reissueToken(String refreshToken) {
        if(jwtTokenProvider.validateToken(refreshToken) != JwtValidationType.VALID_JWT) {
            throw new UnauthorizedException(ErrorMessage.TOKEN_INVALID_ERROR);
        }

        Long memberId = jwtTokenProvider.getUserFromJwt(refreshToken);
        if(!memberId.equals(findIdByRefreshToken(refreshToken))) {
            throw new UnauthorizedException(ErrorMessage.TOKEN_INVALID_ERROR);
        }

        UserAuthentication userAuthentication = UserAuthentication.createUserAuthentication(memberId);
        return ReissueTokenResponse.of(jwtTokenProvider.issueAccessToken(userAuthentication));
    }

    public Long findIdByRefreshToken(String refreshToken) {
        Token token = tokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow( () -> new NotFoundException(ErrorMessage.TOKEN_NOT_FOUND));
        return token.getId();
    }
}
