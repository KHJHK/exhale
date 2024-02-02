package com.ssafy.exhale.controller;

import com.ssafy.exhale.dto.requestDto.SolvedProblemRequest;
import com.ssafy.exhale.dto.responseDto.commonDto.CommonResponse;
import com.ssafy.exhale.dto.responseDto.rehabilitationDto.CategoryResponse;
import com.ssafy.exhale.dto.responseDto.rehabilitationDto.ProblemResponse;
import com.ssafy.exhale.service.RehabilitationService;
import com.ssafy.exhale.util.TokenPayloadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rehabilitation")
@RequiredArgsConstructor
public class CourseController {

    private final RehabilitationService rehabilitationService;
    private final TokenPayloadUtil tokenPayloadUtil;

    @GetMapping
    public ResponseEntity<CommonResponse> getCourseList() {
        return CommonResponse.ok(rehabilitationService.getCourseList());
    }

    @GetMapping("/{course_id}")
    public ResponseEntity<CommonResponse> getCategoryList(@PathVariable("course_id") int courseId) {
        return CommonResponse.ok(rehabilitationService.getCategory(courseId));
    }

    @GetMapping("/problem/{category_id}")
    public ResponseEntity<CommonResponse> getProblemList(@PathVariable("category_id") int categoryId) {
        return CommonResponse.ok(rehabilitationService.getProblemList(categoryId));
    }

    @PostMapping("/result")
    public ResponseEntity<CommonResponse> solveProblem(@RequestBody SolvedProblemRequest solvedProblemRequest, String loginId) {
        return CommonResponse.ok(rehabilitationService.solveProblem(solvedProblemRequest, loginId));
    }
}
