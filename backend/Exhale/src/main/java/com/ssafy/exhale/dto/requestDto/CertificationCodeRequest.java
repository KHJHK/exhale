package com.ssafy.exhale.dto.requestDto;

import com.ssafy.exhale.dto.logicDto.CertificationCodeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CertificationCodeRequest {
    String code;

    public CertificationCodeDto to(String id, Long memberId, String code, LocalDateTime createAt){
        return CertificationCodeDto.of(
                id,
                memberId,
                code,
                createAt
                );
    }
}


