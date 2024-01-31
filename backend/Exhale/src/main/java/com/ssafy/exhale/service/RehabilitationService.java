package com.ssafy.exhale.service;

import com.ssafy.exhale.domain.rehabilitation.Category;
import com.ssafy.exhale.domain.rehabilitation.Course;
import com.ssafy.exhale.dto.responseDto.rehabilitationDto.CategoryResponse;
import com.ssafy.exhale.dto.responseDto.rehabilitationDto.CourseResponse;
import com.ssafy.exhale.repository.rehabilitationRepository.CategoryRepository;
import com.ssafy.exhale.repository.rehabilitationRepository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RehabilitationService {

    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;

    public List<CourseResponse> getCourseList() {
        List<CourseResponse> courseResponseList = new ArrayList<>();
        for(Course course : courseRepository.findAll()) courseResponseList.add(CourseResponse.from(course));
        return courseResponseList;
    }

    public List<CategoryResponse> getCategory(long courseId) {
        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        Optional<Course> course = courseRepository.findById(courseId);
        Course course1 =
        course.


        for(Category category : categoryRepository.findByCourseId(courseId)) categoryResponseList.add(CategoryResponse.from(category));
        return categoryResponseList;
    }
}
