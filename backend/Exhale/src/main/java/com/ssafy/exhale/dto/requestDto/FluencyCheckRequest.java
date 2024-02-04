package com.ssafy.exhale.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FluencyCheckRequest {
    private String question;
    private String answer;
}
