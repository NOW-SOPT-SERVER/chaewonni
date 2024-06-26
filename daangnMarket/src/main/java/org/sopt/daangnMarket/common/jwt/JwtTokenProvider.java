package org.sopt.daangnMarket.common.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.sopt.daangnMarket.common.auth.dto.CustomUserDetails;
import org.sopt.daangnMarket.domain.enums.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final String USER_ID = "userId";

    private static final Long ACCESS_TOKEN_EXPIRATION_TIME = 24 * 60 * 60 * 1000L;

    private static final Long REFRESH_TOKEN_EXPIRATION_TIME = 24 * 60 * 60 * 1000L * 14;

    private final String JWT_SECRET;
    private final SecretKey secretKey;

    public JwtTokenProvider(@Value("${jwt.secret}") String jwtSecret) {
        this.JWT_SECRET = jwtSecret;
        this.secretKey = getSigningKey();
    }

    // Authentication 객체로 AccessToken 발행
    public String issueAccessToken(final CustomUserDetails userDetails) {
        return generateToken(userDetails, ACCESS_TOKEN_EXPIRATION_TIME);
    }

    public String issueRefreshToken(final CustomUserDetails userDetails) {
        return generateToken(userDetails, REFRESH_TOKEN_EXPIRATION_TIME);
    }


    public String generateToken(CustomUserDetails userDetails, Long tokenExpirationTime) {
        final Date now = new Date();
        final Claims claims = Jwts.claims()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenExpirationTime));      // 만료 시간

        // Claim에는 token 생성시간과 만료시간, 그리고 사용자 인증 정보가 들어감
        claims.put(USER_ID, userDetails.getMemberId().toString());

        // 헤더 타입을 설정해주는 Header Param, Claim 을 이용한 정보를 대상으로 암호화하여 Jwt 토큰을 만들어 반환
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // Header
                .setClaims(claims) // Claim
                .signWith(getSigningKey()) // Signature
                .compact();
    }

    private SecretKey getSigningKey() {
        String encodedKey = Base64.getEncoder().encodeToString(JWT_SECRET.getBytes()); //SecretKey 통해 서명 생성
        return Keys.hmacShaKeyFor(encodedKey.getBytes());   //일반적으로 HMAC (Hash-based Message Authentication Code) 알고리즘 사용
    }

    // Token에서 Claim을 추출하는 과정에서 에러가 발생하면 Catch한 후 Validation을 진행
    public JwtValidationType validateToken(String token) {
        try {
            final Claims claims = getBody(token);
            return JwtValidationType.VALID_JWT;
        } catch (MalformedJwtException ex) {
            return JwtValidationType.INVALID_JWT;
        } catch (ExpiredJwtException ex) {
            return JwtValidationType.EXPIRED_JWT;
        } catch (UnsupportedJwtException ex) {
            return JwtValidationType.UNSUPPORTED_JWT;
        } catch (IllegalArgumentException ex) {
            return JwtValidationType.EMPTY_JWT;
        }
    }

    private Claims getBody(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Long getUserFromJwt(String token) {
        Claims claims = getBody(token);
        return Long.valueOf(claims.get(USER_ID).toString());
    }
}
