package org.sopt.daangnMarket.common.auth;

import org.sopt.daangnMarket.common.dto.CustomUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserAuthentication extends UsernamePasswordAuthenticationToken {

    public UserAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public static UserAuthentication createUserAuthentication(CustomUserDetails customUserDetails) {
        return new UserAuthentication(customUserDetails, null, customUserDetails.getAuthorities());
    }

    public Long getMemberId() {
        if (this.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) this.getPrincipal();
            return userDetails.getMemberId();
        }
        return null;  // 적절한 예외 처리를 고려할 수 있음
    }
}
