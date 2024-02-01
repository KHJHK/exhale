package com.ssafy.exhale.dto.logicDto.rehabilitationDto;

import com.ssafy.exhale.domain.rehabilitation.Category;
import com.ssafy.exhale.domain.rehabilitation.TextMatchingProblem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TextMatchingProblemDto {
    private Long id;
    private String question;
    private String questionImage;
    private String option1;
    private String option2;
    private String option3;
    private Integer answer;
    private LocalDateTime createdDate;
    private Boolean isRemoved;
    private Category category;

    static public TextMatchingProblemDto from(TextMatchingProblem textMatchingProblem) {
        return new TextMatchingProblemDto(
                textMatchingProblem.getId(),
                textMatchingProblem.getQuestion(),
                textMatchingProblem.getQuestionImage(),
                textMatchingProblem.getOption1(),
                textMatchingProblem.getOption2(),
                textMatchingProblem.getOption3(),
                textMatchingProblem.getAnswer(),
                textMatchingProblem.getCreatedDate(),
                textMatchingProblem.getIsRemoved(),
                textMatchingProblem.getCategory()
        );
    }
}
