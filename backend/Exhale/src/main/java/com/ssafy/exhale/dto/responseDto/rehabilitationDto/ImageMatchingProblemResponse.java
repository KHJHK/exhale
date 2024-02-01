package com.ssafy.exhale.dto.responseDto.rehabilitationDto;

import com.ssafy.exhale.dto.logicDto.rehabilitationDto.ImageMatchingProblemDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ImageMatchingProblemResponse extends ProblemResponse {
    private final String question;

    private final List<String> options;

    private final int answer;

    private ImageMatchingProblemResponse(long problemId, String question, List<String> options, int answer) {
        super(problemId);
        this.question = question;
        this.options = options;
        this.answer = answer;
    }

    public static ImageMatchingProblemResponse from(ImageMatchingProblemDto imageMatchingProblemDto) {
        List<String> options = new ArrayList<>();
        options.add(imageMatchingProblemDto.getOptionImage1());
        options.add(imageMatchingProblemDto.getOptionImage2());
        options.add(imageMatchingProblemDto.getOptionImage3());
        options.add(imageMatchingProblemDto.getOptionImage4());

        return new ImageMatchingProblemResponse(
                imageMatchingProblemDto.getId(),
                imageMatchingProblemDto.getQuestion(),
                options,
                imageMatchingProblemDto.getAnswer()
        );
    }
}
