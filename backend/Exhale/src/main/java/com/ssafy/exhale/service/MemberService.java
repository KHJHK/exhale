package com.ssafy.exhale.service;

import com.ssafy.exhale.domain.Member;
import com.ssafy.exhale.dto.requestDto.MemberRequest;
import com.ssafy.exhale.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
        member.setName(memberRequest.getName());
        member.setBirth(memberRequest.getBirth());
        member.setEmailDomain(memberRequest.getEmailDomain());
        member.setEmailId(memberRequest.getEmailId());
        member.setLoginId(loginId);
        member.setPassword(bCryptPasswordEncoder.encode(password));
        member.setRole("ROLE_USER");

        memberRepository.save(member);
    }

    public void saveRefreshValue(int member_id, String token){
        memberRepository.findByMemberId(member_id).ifPresent((member) -> {
            member.setRefreshValue(token);
            memberRepository.save(member);
        });

    }

    public boolean compareRefreshToken(int memberId, String tokenValue){

        if(memberRepository.existsByMemberIdAndRefreshValue(memberId, tokenValue))return true;
        return false;
    }
    public boolean checkLoginId(String loginId){
        if(memberRepository.existsByLoginId(loginId))return true;
        return false;
    }

    public boolean checkPassword(int memberId, String password){
        if(memberRepository.existsByMemberIdAndPassword(memberId, bCryptPasswordEncoder.encode(password))) return true;
        else return false;
    }

}
