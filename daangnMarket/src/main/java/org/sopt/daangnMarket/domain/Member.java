package org.sopt.daangnMarket.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴 적용
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private double mannerTemperature;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Location location; //사용자가 인증한 현재 동네

    // 정적 팩토리 메서드
//    public static Member create(String username, String password, String nickname, String phoneNumber, Location location) {
//        Member member = new Member();
//        member.username = username;
//        member.password = password;
//        member.role = "ROLE_ADMIN";
//        member.nickname = nickname;
//        member.phoneNumber = phoneNumber;
//        member.location = location;
//        member.mannerTemperature = 36.5;
//        return member;
//    }
}
