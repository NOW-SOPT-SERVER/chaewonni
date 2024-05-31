package org.sopt.daangnMarket.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.daangnMarket.dto.request.member.MemberCreateDto;
import org.sopt.daangnMarket.dto.response.member.MemberFindDto;
import org.sopt.daangnMarket.service.MemberService;
import org.sopt.daangnMarket.util.ApiResponse;
import org.sopt.daangnMarket.util.ApiUtils;
import org.sopt.daangnMarket.util.dto.SuccessMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;

    //멤버 생성
    @PostMapping("/members")
    public ResponseEntity<ApiResponse<Void>> createMember(
            @Validated @RequestBody MemberCreateDto memberCreate) {
        memberService.createMember(memberCreate);
        return ApiUtils.success(HttpStatus.CREATED, SuccessMessage.MEMBER_CREATE_SUCCESS);
    }

    //멤버 조회
    @GetMapping("/members/{memberId}")
    public ResponseEntity<ApiResponse<MemberFindDto>> findMember(@PathVariable Long memberId) {
        return ApiUtils.success(HttpStatus.OK, SuccessMessage.MEMBER_FIND_SUCCESS, memberService.findMember(memberId));
    }
}
