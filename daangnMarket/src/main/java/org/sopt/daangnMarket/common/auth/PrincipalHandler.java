package org.sopt.daangnMarket.common.auth;

import org.sopt.daangnMarket.common.auth.dto.CustomUserDetails;
import org.sopt.daangnMarket.exception.UnauthorizedException;
import org.sopt.daangnMarket.util.dto.ErrorMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class PrincipalHandler {

    private static final String ANONYMOUS_USER = "anonymousUser";

    public Long getUserIdFromPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        validatePrincipal(principal);

        if (principal instanceof CustomUserDetails userDetails) {
            return userDetails.getMemberId(); // CustomUserDetails 객체에서 사용자 ID 추출
        } else {
            throw new UnauthorizedException(ErrorMessage.JWT_UNAUTHORIZED_EXCEPTION);
        }
    }

    public void validatePrincipal(final Object principal) {
        if (principal == null || principal.toString().equals(ANONYMOUS_USER)) {
            throw new UnauthorizedException(ErrorMessage.JWT_UNAUTHORIZED_EXCEPTION);
        }
    }
}

