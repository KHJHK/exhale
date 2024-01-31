package com.ssafy.exhale.service;

import com.ssafy.exhale.domain.Member;
import com.ssafy.exhale.dto.requestDto.MemberRequest;
import com.ssafy.exhale.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public void join(MemberRequest memberRequest) {

        String loginId = memberRequest.getLoginId();
        String password = memberRequest.getPassword();

        Boolean isExist = memberRepository.existsByLoginId(loginId);
        if (isExist) {
            // todo: 이미 존재하는 아이디 response 처리
            return;
        }

        Member member = new Member();
        member.updateJoin(memberRequest, bCryptPasswordEncoder.encode(password));
        memberRepository.save(member);
    }
    
    public boolean checkLoginId(String loginId){
        if(memberRepository.existsByLoginId(loginId))return true;
        return false;
    }

    public boolean verifyPassword(long id, String newPassword){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        boolean[] isMatch = new boolean[1];
        memberRepository.findById(id).ifPresent((member) -> {
            String oldPassword = member.getPassword();

            isMatch[0] = passwordEncoder.matches(newPassword, oldPassword);
        });
        return isMatch[0];

    }
    public boolean checkPassword(long id, String newPassword){
        return verifyPassword(id,newPassword);
    }
    public boolean changePassword(long id, String currentPassword, String newPassword) {
        if (verifyPassword(id, currentPassword)) {
            memberRepository.findById(id).ifPresent((member) -> {
                member.updatePassword(bCryptPasswordEncoder.encode(newPassword));
                memberRepository.save(member);
            });
        }else{
            return false;
        }
        return true;

    }
    public boolean changeWithdraw(long id){
        memberRepository.findById(id).ifPresent((member) -> {
            member.updateWithdraw(true);
            memberRepository.save(member);
        });
        return true;
    }

    public boolean checkWithdraw(long id){
        AtomicReference<Boolean> isWithdraw = new AtomicReference<>(false);
        memberRepository.findById(id).ifPresent((member)->{
            isWithdraw.set(member.getWithdraw());
        });
        return isWithdraw.get();
    }

}
