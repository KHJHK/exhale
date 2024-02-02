package com.ssafy.exhale.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
    @GetMapping("/test")
    public String testMethod() {
        return "a";
    }
}
