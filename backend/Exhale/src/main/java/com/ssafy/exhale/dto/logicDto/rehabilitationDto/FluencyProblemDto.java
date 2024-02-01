package com.ssafy.exhale.dto.logicDto.rehabilitationDto;

import com.ssafy.exhale.domain.rehabilitation.Category;
import com.ssafy.exhale.domain.rehabilitation.FluencyProblem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FluencyProblemDto {
    private Long id;
    private String question;
    private LocalDateTime createdDate;
    private Boolean isRemoved;
    private Category category;

    static public FluencyProblemDto from (FluencyProblem fluencyProblem) {
        return new FluencyProblemDto(
                fluencyProblem.getId(),
                fluencyProblem.getQuestion(),
                fluencyProblem.getCreatedDate(),
                fluencyProblem.getIsRemoved(),
                fluencyProblem.getCategory()
        );
    }
}
