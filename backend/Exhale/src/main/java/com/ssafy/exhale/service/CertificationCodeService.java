package com.ssafy.exhale.service;

import com.ssafy.exhale.domain.CertificationCode;
import com.ssafy.exhale.dto.logicDto.CertificationCodeDto;
import com.ssafy.exhale.dto.requestDto.CertificationCodeRequest;
import com.ssafy.exhale.repository.CertificationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CertificationCodeService {
    private final CertificationCodeRepository certificationCodeRepository;

    public void saveCode(CertificationCodeDto certificationCodeDto){
        try {
            CertificationCode certificationCode = certificationCodeDto.toEntity();
            certificationCodeRepository.save(certificationCode);
        } catch (Exception e) {
            // todo 예외 처리 : 저장 실패
        }
    }
}
