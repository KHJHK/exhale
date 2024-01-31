package com.ssafy.exhale.dto.responseDto.rehabilitationDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.exhale.domain.rehabilitation.Course;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseResponse {
    @JsonProperty("course_id")
    private long courseId;

    @JsonProperty("course_name")
    private String courseName;

    public static CourseResponse from(Course course) {
        return new CourseResponse(course.getId(), course.getCourseName());
    }
}
