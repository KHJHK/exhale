package com.ssafy.exhale.controller;

import com.ssafy.exhale.dto.requestDto.JoinRequest;
import com.ssafy.exhale.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
@RequiredArgsConstructor
public class CommonController {
    private final MemberService memberService;

    @PostMapping("/join")
    public void join(@RequestBody JoinRequest joinRequest){
        memberService.join(joinRequest);
        // todo: 회원가입 응답 구현
    }
}
