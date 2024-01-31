package com.ssafy.exhale.controller;

import com.ssafy.exhale.dto.responseDto.rehabilitationDto.CategoryResponse;
import com.ssafy.exhale.dto.responseDto.rehabilitationDto.CourseResponse;
import com.ssafy.exhale.service.RehabilitationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rehabilitation")
@RequiredArgsConstructor
public class CourseController {

    private final RehabilitationService rehabilitationService;

    @GetMapping
    public String getCourseList() {
        return null;
    }

    @GetMapping("/{course_id}")
    public String getCategoryList(@PathVariable int courseId) {
        return null;
    }

    @GetMapping("/problem/{category_id}")
    public String getProblemList(@PathVariable int categoryId) {
        return null;
    }
}
