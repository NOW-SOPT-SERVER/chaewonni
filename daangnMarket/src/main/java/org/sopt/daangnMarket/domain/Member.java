package org.sopt.daangnMarket.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.sopt.daangnMarket.domain.enums.Role;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴 적용
public class Member {

    public static final double DEFAULT_MANNER_TEMPERATURE = 36.5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

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

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Bookmark> bookmarks = new ArrayList<>();
}
