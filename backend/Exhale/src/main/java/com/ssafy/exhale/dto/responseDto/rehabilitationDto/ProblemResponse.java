package com.ssafy.exhale.dto.responseDto.rehabilitationDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProblemResponse {
    @JsonProperty("problem_id")
    private long problemId;
}
