package com.ssafy.exhale.dto.requestDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SolvedProblemRequest {
    private long categoryId;

    private long problemId;

    @JsonProperty("is_right")
    private boolean isRight;

    private int time;
}
