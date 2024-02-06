package com.ssafy.exhale.service;

import com.ssafy.exhale.domain.CertificationCode;
import com.ssafy.exhale.dto.logicDto.CertificationCodeDto;
import com.ssafy.exhale.dto.requestDto.CertificationCodeRequest;
import com.ssafy.exhale.repository.CertificationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CertificationCodeService {
    private final CertificationCodeRepository certificationCodeRepository;

    @Transactional
    public void saveCode(CertificationCodeDto certificationCodeDto){
        try {
            certificationCodeRepository.deleteByEmail(certificationCodeDto.getEmail());
            CertificationCode certificationCode = certificationCodeDto.toEntity();
            certificationCodeRepository.save(certificationCode);
        } catch (Exception e) {
            // todo 예외 처리 : 저장 실패
        }
    }

    public boolean compareCode(CertificationCodeRequest certificationCodeRequest){
        String email = certificationCodeRequest.getEmailId()+"@"+certificationCodeRequest.getEmailDomain();
        String code = certificationCodeRequest.getCode();
        return certificationCodeRepository.findByEmail(email)
                .map(certification ->{
                    if(certification.getCode().equals(code)){
                        certificationCodeRepository.deleteById(certification.getId());
                        return true;
                    }else{
                        return false;
                    }
                })
                .orElseGet(()->{
                    // todo 예외 처리
                    System.out.println("예외 발생");
                    return false;
                });
    }
}
