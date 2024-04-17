package org.sopt.daangnMarket.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    @Size(min = 2, max = 10, message = "2글자 이상, 10글자 이하여야 합니다.")
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
