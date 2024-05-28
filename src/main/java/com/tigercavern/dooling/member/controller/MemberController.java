package com.tigercavern.dooling.member.controller;

import com.tigercavern.dooling.member.dto.JoinMemberRequest;
import com.tigercavern.dooling.member.dto.UpdateMemberRequest;
import com.tigercavern.dooling.member.dto.UpdateMemberResponse;
import com.tigercavern.dooling.member.entity.Member;
import com.tigercavern.dooling.member.repository.MemberRepository;
import com.tigercavern.dooling.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping("/api/member/join")
    public ResponseEntity joinMember(@RequestBody /*@Valid*/ JoinMemberRequest request) {

        Member member = new Member(request);
        String loginId = memberService.join(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(loginId);
    }

    @PostMapping("/api/user/{email}")
    public UpdateMemberResponse updateMember(@PathVariable("email") String email,
                                             @RequestBody UpdateMemberRequest request) {
        return memberService.update(email, request);
    }
}
