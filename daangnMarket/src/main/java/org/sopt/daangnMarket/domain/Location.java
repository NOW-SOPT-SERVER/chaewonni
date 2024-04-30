package org.sopt.daangnMarket.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
