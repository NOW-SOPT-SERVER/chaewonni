package org.sopt.practice.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.practice.common.dto.SuccessMessage;
import org.sopt.practice.common.dto.SuccessStatusResponse;
import org.sopt.practice.service.MemberService;
import org.sopt.practice.service.dto.MemberCreateDto;
import org.sopt.practice.service.dto.MemberFindDto;
import org.sopt.practice.service.dto.UserJoinResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<SuccessStatusResponse> postMember(
            @RequestBody MemberCreateDto memberCreate
    ) {
        memberService.createMember(memberCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessStatusResponse.of(SuccessMessage.MEMBER_CREATE_SUCCESS));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberFindDto> findMemberById(
            @PathVariable Long memberId
    ) {
        return ResponseEntity.ok(memberService.findMemberById(memberId));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMember(
            @PathVariable Long memberId
    ) {
        memberService.deleteMemberById(memberId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<MemberFindDto>> findAllMembers() {
        return ResponseEntity.ok(memberService.findAllMembers());
    }

}
