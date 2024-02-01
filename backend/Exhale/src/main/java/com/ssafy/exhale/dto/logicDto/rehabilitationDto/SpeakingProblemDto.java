package com.ssafy.exhale.dto.logicDto.rehabilitationDto;

import com.ssafy.exhale.domain.rehabilitation.Category;
import com.ssafy.exhale.domain.rehabilitation.SpeakingProblem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SpeakingProblemDto {
    private Long id;
    private String question;
    private LocalDateTime createdDate;
    private Boolean isRemoved;
    private String hint;
    private Category category;

    static public SpeakingProblemDto from(SpeakingProblem speakingProblem) {
        return new SpeakingProblemDto(
                speakingProblem.getId(),
                speakingProblem.getQuestion(),
                speakingProblem.getCreatedDate(),
                speakingProblem.getIsRemoved(),
                speakingProblem.getHint(),
                speakingProblem.getCategory()
        );
    }
}
