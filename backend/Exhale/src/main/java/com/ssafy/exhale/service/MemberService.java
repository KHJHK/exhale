package com.ssafy.exhale.service;

import com.ssafy.exhale.domain.Member;
import com.ssafy.exhale.dto.requestDto.JoinRequest;
import com.ssafy.exhale.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public void join(JoinRequest joinRequest) {

        String loginId = joinRequest.getLogin_id();
        String password = joinRequest.getPassword();

        Boolean isExist = memberRepository.existsByLoginId(loginId);
        if (isExist) {
            // todo: 이미 존재하는 아이디 response 처리
            return;
        }

        Member member = new Member();
        member.setName(joinRequest.getName());
        member.setBirth(joinRequest.getBirth());
        member.setEmailDomain(joinRequest.getEmail_domain());
        member.setEmailId(joinRequest.getEmail_id());
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


}
