package org.sopt.daangnMarket.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.sopt.daangnMarket.domain.enums.TransactionType;
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
    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType; // 거래 방식

    @Column
    private int price;

    @Column
    @Size(min = 4, message = "최소 4글자 이상이어야 합니다.")
    private String description; //자세한 설명

    @Column
    private String address; //거래 희망 장소

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SaleStatus saleStatus; //판매 여부

    @ManyToOne
    @JoinColumn(name = "category_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

}
