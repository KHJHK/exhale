package com.ssafy.exhale.dto.responseDto.rehabilitationDto;

import com.ssafy.exhale.dto.logicDto.rehabilitationDto.SpeakingProblemDto;
import lombok.Getter;

@Getter
public class SpeakingProblemResponse extends ProblemResponse {
    private final String question;

    private final String hint;

    private SpeakingProblemResponse(long problemId, String question, String hint) {
        super(problemId);
        this.question = question;
        this.hint = hint;
    }

    public static SpeakingProblemResponse from(SpeakingProblemDto speakingProblemDto) {
        return new SpeakingProblemResponse(
                speakingProblemDto.getId(),
                speakingProblemDto.getQuestion(),
                speakingProblemDto.getHint()
        );
    }
}
