package org.sopt.daangnMarket.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.sopt.daangnMarket.domain.enums.Category;
import org.sopt.daangnMarket.domain.enums.TradeType;
import org.sopt.daangnMarket.domain.enums.SaleStatus;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴 적용
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TradeType tradeType; // 거래 방식

    @Column
    private int price;

    @Column(nullable = false)
    private String description; //자세한 설명

    @Column
    private String address; //거래 희망 장소

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SaleStatus saleStatus; //판매 여부

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Builder.Default
    @Column(nullable = false)
    private int bookmarkCount = 0;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Location registeredLocation; //상품이 등록된 위치

    public void addBookmarkCount() {
        this.bookmarkCount ++;
    }
}
