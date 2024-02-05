package com.ssafy.exhale.dto.requestDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LetterRecodeRequest {
    @NotNull
    @JsonProperty("letter_id")
    private Long letterId;

    @NotNull
    @JsonProperty("correct_cnt")
    private Integer correctCnt;

    @NotNull
    @JsonProperty("wrong_cnt")
    private Integer wrongCnt;
}
