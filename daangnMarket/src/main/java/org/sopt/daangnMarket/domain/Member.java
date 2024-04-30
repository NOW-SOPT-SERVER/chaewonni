package org.sopt.daangnMarket.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    private double mannerTemperature;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Location location; //사용자가 인증한 현재 동네

    // 정적 팩토리 메서드
    public static Member create(String nickname, String phoneNumber, Location location) {
        Member member = new Member();
        member.nickname = nickname;
        member.phoneNumber = phoneNumber;
        member.location = location;
        member.mannerTemperature = 36.5;
        return member;
    }
}