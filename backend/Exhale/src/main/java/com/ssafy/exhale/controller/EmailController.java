package com.ssafy.exhale.controller;

import com.ssafy.exhale.domain.CertificationCode;
import com.ssafy.exhale.dto.logicDto.CertificationCodeDto;
import com.ssafy.exhale.dto.logicDto.MemberDto;
import com.ssafy.exhale.dto.requestDto.CertificationCodeRequest;
import com.ssafy.exhale.dto.requestDto.EmailRequest;
import com.ssafy.exhale.dto.requestDto.MemberRequest;
import com.ssafy.exhale.service.CertificationCodeService;
import com.ssafy.exhale.service.MemberService;
import com.ssafy.exhale.util.EmailUtil;
import com.ssafy.exhale.util.GenerateCertificationCode;
import com.ssafy.exhale.util.TokenPayloadUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {
    private final TokenPayloadUtil tokenPayloadUtil;
    private final MemberService memberService;
    private final EmailUtil emailUtil;
    private final CertificationCodeService certificationCodeService;

    @PostMapping("/certification")
    public ResponseEntity<?> certificationEmail(@RequestBody EmailRequest emailRequest){
        Long memberId = tokenPayloadUtil.getMemberId();
        if(memberService.compareEmail(memberId, emailRequest)) {
            String fullEmail = emailRequest.getEmailId() + "@" + emailRequest.getEmailDomain();
            String certificationNumber = GenerateCertificationCode.getCertificationNumber();
            certificationCodeService.saveCode(CertificationCodeDto.of(null, memberId, certificationNumber, null));
            try {
                emailUtil.sendCertificationMail(fullEmail, certificationNumber);
            } catch (MessagingException e) {
                throw new RuntimeException(e);  // todo 예외처리 수정
            }
        }

        return ResponseEntity.status(200).body("");
    }

    @PostMapping("/check")
    public ResponseEntity<?> checkCertificationCode(@RequestBody CertificationCodeRequest certificationCodeRequest){
        Long memberId = tokenPayloadUtil.getMemberId();
        String code = certificationCodeRequest.getCode();
        if(code==null){
            // todo 예외 처리 : 파라미터 오류
        }
        boolean isSuccess = certificationCodeService.compareCode(memberId, code);
        if(isSuccess) return ResponseEntity.status(200).body("");
        else return ResponseEntity.status(400).body("인증코드가 맞지 않습니다.");
    }

    @PostMapping("/temp-password")
    public ResponseEntity<?> tempPassword(@RequestBody MemberRequest memberRequest){

        MemberDto memberDto = memberService.checkLoginIdEmail(memberRequest);
        if(memberDto.getEmailId()==null || memberDto.getEmailDomain()==null){
            // todo 예외처리 이메일 없음
        }
        String fullEmail = memberDto.getEmailId()+"@"+memberDto.getEmailDomain();
        String tempPassword = GenerateCertificationCode.getRandomPassword();
        memberService.saveTempPassword(memberDto.getId(), tempPassword);
        try {
            emailUtil.sendTempPasswordMail(fullEmail, tempPassword);
        } catch (MessagingException e) {
            throw new RuntimeException(e);  // todo 예외처리 수정
        }

        return ResponseEntity.ok("");
    }
}
