package org.sopt.daangnMarket.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.daangnMarket.dto.Member.request.MemberCreateDto;
import org.sopt.daangnMarket.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    //멤버 생성
    @PostMapping
    public ResponseEntity<Void> createMember(
            @RequestBody MemberCreateDto memberCreate) {
        return ResponseEntity.created(URI.create(memberService.createMember(memberCreate))).build();
    }
}
