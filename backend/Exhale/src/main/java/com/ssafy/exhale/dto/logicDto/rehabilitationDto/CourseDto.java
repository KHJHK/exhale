package com.ssafy.exhale.dto.logicDto.rehabilitationDto;

import com.ssafy.exhale.domain.rehabilitation.Course;
import com.ssafy.exhale.dto.responseDto.rehabilitationDto.CourseResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseDto {
    private Long id;
    private String courseName;
    private LocalDateTime createdTime;
    private Boolean isRemoved;

    static public CourseDto from(Course course) {
        return new CourseDto(
                course.getId(),
                course.getCourseName(),
                course.getCreatedTime(),
                course.getIsRemoved()
        );
    }
}