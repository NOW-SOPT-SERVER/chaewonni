package org.sopt.daangnMarket.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private double MannerTemperature;

    // 정적 팩토리 메서드
    public static Member create(String nickname, String phoneNumber) {
        Member member = new Member();
        member.nickname = nickname;
        member.phoneNumber = phoneNumber;
        member.MannerTemperature = 36.5;
        return member;
    }
}
