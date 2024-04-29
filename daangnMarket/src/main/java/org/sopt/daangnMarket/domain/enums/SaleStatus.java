package org.sopt.daangnMarket.domain.enums;

import lombok.Getter;

@Getter
public enum SaleStatus {

    FOR_SALE("판매중"),
    RESERVED("예약중"),
    SOLD_OUT("거래완료");

    private final String koreanName;

    SaleStatus(String koreanName) {
        this.koreanName = koreanName;
    }

    public static SaleStatus fromKoreanName(String koreanName) {
        for (SaleStatus saleStatus : SaleStatus.values()) {
            if (saleStatus.getKoreanName().equals(koreanName)) {
                return saleStatus;
            }
        }
        return null;
    }
}