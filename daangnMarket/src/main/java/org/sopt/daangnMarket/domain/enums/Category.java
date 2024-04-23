package org.sopt.daangnMarket.domain.enums;

import lombok.Getter;

@Getter
public enum Category {

    DIGITAL_DEVICES("디지털기기"),
    HOME_APPLIANCES("생활가전"),
    FURNITURE_INTERIOR("가구/인테리어"),
    KITCHEN_LIFE("생활/주방"),
    BABY_KIDS("유아동"),
    BABY_BOOKS("유아도서"),
    WOMENS_CLOTHING("여성의류"),
    WOMENS_ACCESSORIES("여성잡화"),
    MENS_FASHION_ACCESSORIES("남성패션/잡화"),
    BEAUTY("뷰티/미용"),
    SPORTS_LEISURE("스포츠/레저"),
    HOBBIES_GAMES_MUSIC("취미/게임/음반"),
    BOOKS("도서"),
    TICKETS_EXCHANGE_VOUCHERS("티켓/교환권"),
    PROCESSED_FOODS("가공식품"),
    PET_SUPPLIES("반려동물용품"),
    PLANTS("식물"),
    MISCELLANEOUS_GOODS("기타 중고물품"),
    BUY("삽니다");

    private final String koreanName;

    Category(String koreanName) {
        this.koreanName = koreanName;
    }

    public static Category fromKoreanName(String koreanName) {
        for (Category category : Category.values()) {
            if (category.getKoreanName().equals(koreanName)) {
                return category;
            }
        }
        return null;
    }
}
