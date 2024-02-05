package com.ssafy.exhale.service;

import com.ssafy.exhale.domain.Member;
import com.ssafy.exhale.dto.logicDto.MemberDto;
import com.ssafy.exhale.dto.requestDto.EmailRequest;
import com.ssafy.exhale.dto.requestDto.MemberRequest;
import com.ssafy.exhale.dto.requestDto.NicknameRequest;
import com.ssafy.exhale.exception.handler.DuplicateDataException;
import com.ssafy.exhale.exception.handler.NoSuchDataException;
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

    public void join(MemberDto memberDto) {
        String loginId = memberDto.getLoginId();
        String password = memberDto.getPassword();

        Boolean isExist = memberRepository.existsByLoginId(loginId);
        if (isExist) {
             throw new DuplicateDataException("이미 존재하는 아이디");
        }
        memberDto.setRole("ROLE_USER");
        memberDto.setPassword(bCryptPasswordEncoder.encode(password));
        memberDto.setWithdraw(false);
        Member member = memberDto.toEntity();
        memberRepository.save(member);
    }
    
    public boolean checkLoginId(String loginId){
        return memberRepository.existsByLoginIdAndWithdrawFalse(loginId);
    }

    public boolean verifyPassword(Long id, String newPassword){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Member member = memberRepository.findById(id).orElseThrow(NoSuchDataException::new);
        return passwordEncoder.matches(newPassword, member.getPassword());
    }

    public boolean checkPassword(Long id, String newPassword){
        return verifyPassword(id, newPassword);
    }

    public boolean changePassword(Long id, String currentPassword, String newPassword) {
        if (verifyPassword(id, currentPassword)) {
            Member member = memberRepository.findById(id).orElseThrow(NoSuchDataException::new);
            MemberDto memberDto = MemberDto.from(member);
            memberDto.setPassword(bCryptPasswordEncoder.encode(newPassword));
            memberRepository.save(memberDto.toEntity());
        } else {
            return false;
        }
        return true;
    }

    public void changeWithdraw(Long id){
        Member member = memberRepository.findById(id).orElseThrow(NoSuchDataException::new);
        MemberDto memberDto = MemberDto.from(member);
        memberDto.setWithdraw(true);
        memberRepository.save(memberDto.toEntity());
    }

    public boolean checkWithdraw(Long id){
        AtomicReference<Boolean> isWithdraw = new AtomicReference<>(false);
        Member member = memberRepository.findById(id).orElseThrow(NoSuchDataException::new);
        isWithdraw.set(member.getWithdraw());
        return isWithdraw.get();
    }
    
    public boolean compareEmail(Long id, EmailRequest emailRequest){
        String newEmailId = emailRequest.getEmailId();
        String newEamilDomain = emailRequest.getEmailDomain();
        boolean isEmailMatching = memberRepository.findById(id)
                .map((member) -> {
                    String emailId = member.getEmailId();
                    String emailDomain = member.getEmailDomain();
                    if(emailId==null || emailDomain==null){
                        // todo 예외 처리 : 저장된 이메일 없음
                        System.out.println("저장된 이메일 없음");
                    }
                    return (newEmailId.equals(emailId) && newEamilDomain.equals(emailDomain));
                })
                .orElseGet(() -> {
                    // todo 예외 처리
                    System.out.println("이메일이 다릅니다.");
                    return false;
                });
        return isEmailMatching;
    }

    public boolean checkEmail(EmailRequest emailRequest){
        String emailId = emailRequest.getEmailId();
        String emailDomain = emailRequest.getEmailDomain();
        return memberRepository.existsByEmailIdAndEmailDomain(emailId, emailDomain);
    }

    public void setNickname(Long id, NicknameRequest nicknameRequest){
        memberRepository.findById(id).ifPresentOrElse((member)->{
            MemberDto memberDto = MemberDto.from(member);
            memberDto.setNickname(nicknameRequest.getNickname());
            memberRepository.save(memberDto.toEntity());
        },()->{
            System.out.println("예외처리");
            // todo 예외 처리
        });
    }

    public MemberDto checkLoginIdEmail(MemberRequest memberRequest){
        String loginId = memberRequest.getLoginId();
        String emailId = memberRequest.getEmailId();
        String emailDomain = memberRequest.getEmailDomain();
        MemberDto memberDto = memberRepository.findByLoginIdAndEmailIdAndEmailDomainAndWithdrawFalse(loginId,emailId,emailDomain)
                .map(member -> MemberDto.from(member))
                .orElseGet(() -> {
                    // todo: 값이 없을 때 수행할 작업

                    return null;
                });
        return memberDto;
    }

    public void saveTempPassword(Long id, String password){
        memberRepository.findById(id).ifPresentOrElse((member)->{
            MemberDto memberDto = MemberDto.from(member);
            memberDto.setPassword(bCryptPasswordEncoder.encode(password));
            memberRepository.save(memberDto.toEntity());
        },()->{
            // todo 예외 처리
        });
    }

}
