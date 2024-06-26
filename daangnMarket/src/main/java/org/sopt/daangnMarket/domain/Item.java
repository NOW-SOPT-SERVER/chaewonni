package org.sopt.daangnMarket.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.sopt.daangnMarket.domain.enums.Category;
import org.sopt.daangnMarket.domain.enums.TradeType;
import org.sopt.daangnMarket.domain.enums.SaleStatus;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Column
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Location registeredLocation; //상품이 등록된 위치

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Bookmark> bookmarks = new ArrayList<>();

    public void addBookmarkCount() {
        this.bookmarkCount ++;
    }
}
