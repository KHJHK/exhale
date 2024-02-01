package com.ssafy.exhale.dto.logicDto.rehabilitationDto;

import com.ssafy.exhale.domain.rehabilitation.Category;
import com.ssafy.exhale.domain.rehabilitation.ImageMatchingProblem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageMatchingProblemDto {
    private Long id;
    private String question;
    private String optionImage1;
    private String optionImage2;
    private String optionImage3;
    private String optionImage4;
    private Integer answer;
    private LocalDateTime createdDate;
    private Boolean isRemoved;
    private Category category;

    static public ImageMatchingProblemDto from(ImageMatchingProblem imageMatchingProblem) {
        return new ImageMatchingProblemDto(
                imageMatchingProblem.getId(),
                imageMatchingProblem.getQuestion(),
                imageMatchingProblem.getOptionImage1(),
                imageMatchingProblem.getOptionImage2(),
                imageMatchingProblem.getOptionImage3(),
                imageMatchingProblem.getOptionImage4(),
                imageMatchingProblem.getAnswer(),
                imageMatchingProblem.getCreatedDate(),
                imageMatchingProblem.getIsRemoved(),
                imageMatchingProblem.getCategory()
        );
    }
}
