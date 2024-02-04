package com.ssafy.exhale.dto.requestDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SolvedProblemRequest {
    @JsonProperty("problem_id")
    private long problemId;

    @JsonProperty("is_right")
    private boolean isRight;

    private int time;
}
