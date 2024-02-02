package com.ssafy.exhale.controller;

import com.ssafy.exhale.dto.logicDto.AuthenticationDto;
import com.ssafy.exhale.dto.requestDto.MemberRequest;
import com.ssafy.exhale.dto.requestDto.NicknameRequest;
import com.ssafy.exhale.dto.requestDto.PasswordRequest;
import com.ssafy.exhale.dto.responseDto.TokenInfo;
import com.ssafy.exhale.service.AuthenticationService;
import com.ssafy.exhale.service.MemberService;
import com.ssafy.exhale.util.TokenPayloadUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final TokenPayloadUtil tokenPayloadUtil;
    private final AuthenticationService authenticationService;

    @PostMapping("/refresh")
    public ResponseEntity<?> getRefreshToken(HttpServletRequest request) {

        // DB와 토큰 비교
        String token_value = request.getHeader("Authorization").split(" ")[1];
        long memberId = tokenPayloadUtil.getMemberId();
        Map<String, Object> responseBody = new HashMap<>();

        if (authenticationService.compareRefreshToken(memberId, token_value)) {
            // 새로운 토큰 발행
            String jwt = tokenPayloadUtil.createJWT();
            String refreshToken = tokenPayloadUtil.createRefreshToken();

            AuthenticationDto authenticationDto = AuthenticationDto.of();
            authenticationDto.setMemberId(memberId);
            authenticationDto.setRefreshValue(refreshToken);
            authenticationService.reSaveRefreshValue(authenticationDto);

            TokenInfo tokeninfo = new TokenInfo("Bearer " + jwt, "Bearer " + refreshToken);

            responseBody.put("token", tokeninfo);

        } else {
            return ResponseEntity.status(401).body("Unauthorized: Invalid token");
        }
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/check-password")
    public ResponseEntity<?> checkPassword(@RequestBody MemberRequest memberRequest){
        if(memberService.checkPassword(tokenPayloadUtil.getMemberId(),memberRequest.getPassword()))return ResponseEntity.ok("{result: true}");
        return ResponseEntity.status(200).body("{result: false}");
    }

    @PostMapping("/repassword")
    public ResponseEntity<?> changePassword(@RequestBody PasswordRequest passwordRequest){
        if(memberService.changePassword(tokenPayloadUtil.getMemberId(),passwordRequest.getOldPassword(),passwordRequest.getNewPassword()))return ResponseEntity.ok("{result: true}");
        return ResponseEntity.status(200).body("{result: false}");
    }
    @GetMapping("/withdraw")
    public ResponseEntity<?> withdrawal(){
        memberService.changeWithdraw(tokenPayloadUtil.getMemberId());
        return ResponseEntity.status(200).body("");
    }
    @GetMapping("/logout")
    public ResponseEntity<?> logout(){
        authenticationService.logout(tokenPayloadUtil.getMemberId());
        return ResponseEntity.status(200).body("");
    }

    @PostMapping("/nickname")
    public ResponseEntity<?> setNickname(@RequestBody NicknameRequest nicknameRequest){
        Long memberId = tokenPayloadUtil.getMemberId();
        memberService.setNickname(memberId, nicknameRequest);
        return ResponseEntity.status(200).body("");
    }

}
