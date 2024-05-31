package org.sopt.daangnMarket.common.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.sopt.daangnMarket.common.auth.UserAuthentication;
import org.sopt.daangnMarket.common.auth.dto.CustomUserDetails;
import org.sopt.daangnMarket.common.jwt.JwtTokenProvider;
import org.sopt.daangnMarket.domain.Member;
import org.sopt.daangnMarket.exception.UnauthorizedException;
import org.sopt.daangnMarket.util.dto.ErrorMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.sopt.daangnMarket.common.jwt.JwtValidationType.VALID_JWT;
// 요청에서 Jwt를 검증하는 커스텀 필터 클래스
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter { // 요청이 주어졌을 때, 한 번만 수행되는 필터를 상속받음

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            final String token = getJwtFromRequest(request);
            if (jwtTokenProvider.validateToken(token) == VALID_JWT) {

                Long memberId = jwtTokenProvider.getUserFromJwt(token);

                String username = jwtTokenProvider.getUsername(token);
                String role = jwtTokenProvider.getRole(token);

                Member member = Member.builder()
                        .id(memberId)
                        .username(username)
                        .password("temppassword")
                        .role(role)
                        .build();

                CustomUserDetails customUserDetails = new CustomUserDetails(member);

                UserAuthentication authentication = UserAuthentication.createUserAuthentication(customUserDetails);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception exception) {
            throw new UnauthorizedException(ErrorMessage.JWT_UNAUTHORIZED_EXCEPTION);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }
}
