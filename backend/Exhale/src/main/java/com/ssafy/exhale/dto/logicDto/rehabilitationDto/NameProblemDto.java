package com.ssafy.exhale.dto.logicDto.rehabilitationDto;

import com.ssafy.exhale.domain.rehabilitation.Category;
import com.ssafy.exhale.domain.rehabilitation.NameProblem;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NameProblemDto {
    private Long id;
    private String questionImage;
    private String answer;
    private String hint;
    private LocalDateTime createdDate;
    private Boolean isRemoved;
    private Category category;

    static public NameProblemDto from(NameProblem nameProblem) {
        return new NameProblemDto(
                nameProblem.getId(),
                nameProblem.getQuestionImage(),
                nameProblem.getAnswser(),
                nameProblem.getHint(),
                nameProblem.getCreatedDate(),
                nameProblem.getIsRemoved(),
                nameProblem.getCategory()
        );
    }
}
