package com.ssafy.exhale.dto.responseDto.rehabilitationDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.exhale.dto.logicDto.rehabilitationDto.TextMatchingProblemDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TextMatchingProblemResponse extends ProblemResponse {
    private final String question;

    @JsonProperty("question_image")
    private final String questionImage;

    private final List<String> options;

    private final int answer;

    private TextMatchingProblemResponse(long problemId, String question, String questionImage,
                                        List<String> options, int answer) {
        super(problemId);
        this.question = question;
        this.questionImage = questionImage;
        this.options = options;
        this.answer = answer;
    }

    public static TextMatchingProblemResponse from(TextMatchingProblemDto textMatchingProblemDto) {
        List<String> options = new ArrayList<>();

        options.add(textMatchingProblemDto.getOption1());
        options.add(textMatchingProblemDto.getOption2());
        options.add(textMatchingProblemDto.getOption3());

        return new TextMatchingProblemResponse(
                textMatchingProblemDto.getId(),
                textMatchingProblemDto.getQuestion(),
                textMatchingProblemDto.getQuestionImage(),
                options,
                textMatchingProblemDto.getAnswer()
        );
    }
}
