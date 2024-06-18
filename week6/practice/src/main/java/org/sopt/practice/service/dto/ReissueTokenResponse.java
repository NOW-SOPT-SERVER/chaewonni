package org.sopt.practice.service.dto;

public record ReissueTokenResponse(
        String accessToken
) {
    public static ReissueTokenResponse of(String accessToken) {
        return new ReissueTokenResponse(accessToken);
    }
}
