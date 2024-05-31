package org.sopt.daangnMarket.common.auth.redis.service;

import lombok.RequiredArgsConstructor;
import org.sopt.daangnMarket.common.auth.UserAuthentication;
import org.sopt.daangnMarket.common.auth.redis.domain.Token;
import org.sopt.daangnMarket.common.auth.redis.repository.TokenRepository;
import org.sopt.daangnMarket.common.auth.dto.CustomUserDetails;
import org.sopt.daangnMarket.common.jwt.JwtTokenProvider;
import org.sopt.daangnMarket.common.jwt.JwtValidationType;
import org.sopt.daangnMarket.domain.Member;
import org.sopt.daangnMarket.dto.response.token.ReissueTokenResponse;
import org.sopt.daangnMarket.exception.NotFoundException;
import org.sopt.daangnMarket.exception.UnauthorizedException;
import org.sopt.daangnMarket.repository.MemberRepository;
import org.sopt.daangnMarket.util.dto.ErrorMessage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final TokenRepository tokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    public ReissueTokenResponse reissueToken(String refreshToken) {
        if(jwtTokenProvider.validateToken(refreshToken) != JwtValidationType.VALID_JWT) {
            throw new UnauthorizedException(ErrorMessage.TOKEN_INVALID_ERROR);
        }

        Long memberId = jwtTokenProvider.getUserFromJwt(refreshToken);
        if(!memberId.equals(findIdByRefreshToken(refreshToken))) {
            throw new UnauthorizedException(ErrorMessage.TOKEN_INVALID_ERROR);
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND));

        CustomUserDetails customUserDetails = new CustomUserDetails(member);

        UserAuthentication userAuthentication = UserAuthentication.createUserAuthentication(customUserDetails);
        return ReissueTokenResponse.of(jwtTokenProvider.issueAccessToken(userAuthentication));
    }

    public Long findIdByRefreshToken(String refreshToken) {
        Token token = tokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow( () -> new NotFoundException(ErrorMessage.TOKEN_NOT_FOUND));
        return token.getId();
    }
}
