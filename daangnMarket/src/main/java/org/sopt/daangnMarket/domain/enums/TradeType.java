package org.sopt.daangnMarket.domain.enums;

import lombok.Getter;

@Getter
public enum TradeType {

    SELL("판매하기"),
    FREE("나눔하기");

    private final String koreanName;

    TradeType(String koreanName) {
        this.koreanName = koreanName;
    }

    public static TradeType fromKoreanName(String koreanName) {
        for (TradeType tradeType : TradeType.values()) {
            if (tradeType.getKoreanName().equals(koreanName)) {
                return tradeType;
            }
        }
        return null;
    }
}
