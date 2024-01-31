package com.ssafy.exhale.service;

import com.ssafy.exhale.domain.Member;
import com.ssafy.exhale.dto.logicDto.MemberDto;
import com.ssafy.exhale.dto.requestDto.MemberRequest;
import com.ssafy.exhale.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public void join(MemberDto memberDto) {

        String loginId = memberDto.getLoginId();
        String password = memberDto.getPassword();

        Boolean isExist = memberRepository.existsByLoginId(loginId);
        if (isExist) {
            // todo: 이미 존재하는 아이디 response 처리
            return;
        }
        memberDto.setRole("ROLE_USER");
        memberDto.setPassword(bCryptPasswordEncoder.encode(password));
        memberDto.setWithdraw(false);
        Member member = memberDto.toEntity();
        memberRepository.save(member);
    }
    
    public boolean checkLoginId(String loginId){
        if(memberRepository.existsByLoginId(loginId))return true;
        return false;
    }

    public boolean verifyPassword(Long id, String newPassword){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        boolean[] isMatch = new boolean[1];
        memberRepository.findById(id).ifPresentOrElse(member ->{
            String oldPassword = member.getPassword();
            isMatch[0] = passwordEncoder.matches(newPassword, oldPassword);

        },()->{
            // todo 에러 처리
        });
        return isMatch[0];

    }
    public boolean checkPassword(Long id, String newPassword){
        return verifyPassword(id,newPassword);
    }
    public boolean changePassword(Long id, String currentPassword, String newPassword) {
        if (verifyPassword(id, currentPassword)) {
            memberRepository.findById(id).ifPresentOrElse(member ->{
                MemberDto memberDto = MemberDto.from(member);
                memberDto.setPassword(bCryptPasswordEncoder.encode(newPassword));
                memberRepository.save(memberDto.toEntity());
            },() -> {
                // todo 예외 처리
            });

        }else{
            return false;
        }
        return true;

    }
    public void changeWithdraw(Long id){
        memberRepository.findById(id).ifPresentOrElse((member) -> {
            MemberDto memberDto = MemberDto.from(member);
            memberDto.setWithdraw(true);
            memberRepository.save(memberDto.toEntity());
        },()->{
            // todo 예외 처리
        });
    }

    public boolean checkWithdraw(Long id){
        AtomicReference<Boolean> isWithdraw = new AtomicReference<>(false);
        memberRepository.findById(id).ifPresentOrElse((member)->{
            isWithdraw.set(member.getWithdraw());
        },()->{
            // todo 예외 처리
        });
        return isWithdraw.get();
    }

}
