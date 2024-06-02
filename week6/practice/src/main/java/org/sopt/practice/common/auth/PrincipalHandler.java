package org.sopt.practice.common.auth;

import org.sopt.practice.common.auth.dto.CustomUserDetails;
import org.sopt.practice.common.dto.ErrorMessage;
import org.sopt.practice.exception.UnauthorizedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class PrincipalHandler {

    private static final String ANONYMOUS_USER = "anonymousUser";

    public Long getUserIdFromPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        isPrincipalNull(principal);

        if (principal instanceof CustomUserDetails userDetails) {
            return userDetails.getMemberId(); // CustomUserDetails 객체에서 사용자 ID 추출
        } else {
            throw new IllegalArgumentException("Principal is not an instance of CustomUserDetails");
        }
    }

    public void isPrincipalNull(final Object principal) {
        if (principal == null || principal.toString().equals(ANONYMOUS_USER)) {
            throw new UnauthorizedException(ErrorMessage.JWT_UNAUTHORIZED_EXCEPTION);
        }
    }
}
