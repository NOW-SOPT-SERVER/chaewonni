package org.sopt.daangnMarket.domain.enums;

import lombok.Getter;

@Getter
public enum TransactionType {

    SELL("판매하기"),
    FREE("나눔하기");

    private final String koreanName;

    TransactionType(String koreanName) {
        this.koreanName = koreanName;
    }

    public static TransactionType fromKoreanName(String koreanName) {
        for (TransactionType transactionType : TransactionType.values()) {
            if (transactionType.getKoreanName().equals(koreanName)) {
                return transactionType;
            }
        }
        return null;
    }
}
