package org.sopt.daangnMarket.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String city; // 시/도

    @Column(nullable = false)
    private String district; // 구/군

    @Column(nullable = false)
    private String street; //동

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "registeredLocation", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();
}
