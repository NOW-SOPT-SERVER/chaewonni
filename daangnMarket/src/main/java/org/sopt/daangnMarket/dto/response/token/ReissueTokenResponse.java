package org.sopt.daangnMarket.dto.response.token;

public record ReissueTokenResponse(
        String accessToken
) {
    public static ReissueTokenResponse of(String accessToken) {
        return new ReissueTokenResponse(accessToken);
    }
}
