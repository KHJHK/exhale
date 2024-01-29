package com.ssafy.exhale.service;

import com.ssafy.exhale.domain.Member;
import com.ssafy.exhale.dto.requestDto.MemberRequest;
import com.ssafy.exhale.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public void saveRefreshValue(long member_id, String token){
        memberRepository.findByMemberId(member_id).ifPresent((member) -> {
            member.updateRefreshValue(token);
            memberRepository.save(member);
        });

    }

    public boolean compareRefreshToken(long memberId, String tokenValue){

        if(memberRepository.existsByMemberIdAndRefreshValue(memberId, tokenValue))return true;
        return false;
    }
    public boolean checkLoginId(String loginId){
        if(memberRepository.existsByLoginId(loginId))return true;
        return false;
    }

    public boolean verifyPassword(long memberId, String newPassword){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        boolean[] isMatch = new boolean[1];
        memberRepository.findByMemberId(memberId).ifPresent((member) -> {
            String oldPassword = member.getPassword();

            isMatch[0] = passwordEncoder.matches(newPassword, oldPassword);
        });
        return isMatch[0];

    }
    public boolean checkPassword(long memberId, String newPassword){
        return verifyPassword(memberId,newPassword);
    }
    public boolean changePassword(long memberId, String currentPassword, String newPassword) {
        if (verifyPassword(memberId, currentPassword)) {
            memberRepository.findByMemberId(memberId).ifPresent((member) -> {
                member.updatePassword(bCryptPasswordEncoder.encode(newPassword));
                memberRepository.save(member);
            });
        }else{
            return false;
        }
        return true;

    }
    public boolean changeWithdraw(long memberId){
        memberRepository.findByMemberId(memberId).ifPresent((member) -> {
            member.updateWithdraw(true);
            memberRepository.save(member);
        });
        return true;
    }

}
