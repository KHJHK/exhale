package com.ssafy.exhale.dto.requestDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LetterRecodeRequest {
    @JsonProperty("letter_id")
    private Long letterId;

    @JsonProperty("correct_cnt")
    private Integer correctCnt;

    @JsonProperty("wrong_cnt")
    private Integer wrongCnt;
}
