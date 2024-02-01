package com.ssafy.exhale.dto.logicDto.rehabilitationDto;

import com.ssafy.exhale.domain.rehabilitation.Category;
import com.ssafy.exhale.domain.rehabilitation.Course;
import com.ssafy.exhale.domain.rehabilitation.ProblemTableName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryDto {
    private Long id;
    private String categoryName;
    private Integer overTimeSec;
    private LocalDateTime createdTime;
    private Boolean isRemoved;
    private ProblemTableName problemTableName;
    private Course course;

    static public CategoryDto from(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getCategoryName(),
                category.getOverTimeSec(),
                category.getCreatedTime(),
                category.getIsRemoved(),
                category.getProblemTableName(),
                category.getCourse()
        );
    }
}
