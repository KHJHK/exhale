package com.ssafy.exhale.dto.responseDto.rehabilitationDto;

import com.ssafy.exhale.dto.logicDto.rehabilitationDto.FluencyProblemDto;
import lombok.Getter;

@Getter
public class FluencyProblemResponse extends ProblemResponse {
    private final String question;

    private FluencyProblemResponse(long questionId, String question) {
        super(questionId);
        this.question = question;
    }

    public static FluencyProblemResponse from(FluencyProblemDto fluencyProblemDto) {
        return new FluencyProblemResponse(
                fluencyProblemDto.getId(),
                fluencyProblemDto.getQuestion()
        );
    }
}
