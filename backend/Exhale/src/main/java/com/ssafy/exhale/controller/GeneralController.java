package com.ssafy.exhale.controller;

import com.ssafy.exhale.dto.logicDto.CustomUserDetails;
import com.ssafy.exhale.dto.requestDto.MemberRequest;
import com.ssafy.exhale.dto.responseDto.MemberResponse;
import com.ssafy.exhale.dto.responseDto.TokenInfo;
import com.ssafy.exhale.service.MemberService;
import com.ssafy.exhale.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/general")
public class GeneralController {
    private final MemberService memberService;
    private final JWTUtil jwtUtil;

    @PostMapping("/join")
    public void join(@RequestBody MemberRequest memberRequest){
        memberService.join(memberRequest);
        // todo: 회원가입 응답 구현
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(){
        Map<String, Object> responseBody = new HashMap<>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

            String loginId = customUserDetails.getUsername();
            long memberId = customUserDetails.getMemberId();
            String nickname = customUserDetails.getNickname();

            //roll 값
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
            GrantedAuthority auth = iterator.next();
            String role = auth.getAuthority();


            String jwt = jwtUtil.createJwt(loginId, memberId, role);
            String refresh_token = jwtUtil.createRefreshToken(memberId,role);
            memberService.saveRefreshValue(memberId, refresh_token);

            TokenInfo tokeninfo = new TokenInfo("Bearer "+jwt,"Bearer "+refresh_token);
            MemberResponse memberResponse = new MemberResponse(memberId,loginId,nickname);

            responseBody.put("token",tokeninfo);
            responseBody.put("member",memberResponse);

        }else{
            return ResponseEntity.status(500).body("서버 에러");
        }

        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/id")
    public ResponseEntity<?> checkLoginId(@RequestBody MemberRequest memberRequest){
        if(memberService.checkLoginId(memberRequest.getLoginId()))return ResponseEntity.ok("{result: false}");
        return ResponseEntity.status(200).body("{result: true}");
    }
}
