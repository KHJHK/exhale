package com.ssafy.exhale.service;

import com.ssafy.exhale.domain.rehabilitation.*;
import com.ssafy.exhale.domain.rehabilitation.ProblemTable;
import com.ssafy.exhale.dto.logicDto.rehabilitationDto.*;
import com.ssafy.exhale.domain.rehabilitation.CategoryName;
import com.ssafy.exhale.dto.requestDto.SolvedProblemRequest;
import com.ssafy.exhale.dto.responseDto.rehabilitationDto.*;
import com.ssafy.exhale.exception.handler.NoSuchParameterException;
import com.ssafy.exhale.repository.rehabilitationRepository.*;
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
    private final NameProblemRepository nameProblemRepository;
    private final ImageMatchingProblemRepository imageMatchingProblemRepository;
    private final TextMatchingProblemRepository textMatchingProblemRepository;
    private final SpeakingProblemRepository speakingProblemRepository;
    private final FluencyProblemRepository fluencyProblemRepository;


    public List<CourseResponse> getCourseList() {
        List<CourseResponse> courseResponseList = new ArrayList<>();
        for(Course course : courseRepository.findByIsRemoved(false)) {
            courseResponseList.add(CourseResponse.from(course));
        }
        return courseResponseList;
    }

    public List<CategoryResponse> getCategory(long courseId) {
        Optional<Course> courseOpt = courseRepository.findByIdAndIsRemoved(courseId, false);
        Course course = courseOpt.orElseThrow(NoSuchParameterException::new);

        List<CategoryResponse> categoryResponsesList = new ArrayList<>();
        for(Category category : categoryRepository.findByCourse(course)) {
            categoryResponsesList.add(CategoryResponse.from(category));
        }
        return categoryResponsesList;
    }

    public List<? extends ProblemResponse> getProblemList(long categoryId) {
        Optional<Category> categoryOpt = categoryRepository.findById(categoryId);
        Category category = categoryOpt.orElseThrow(NoSuchParameterException::new);

        List<ProblemResponse> problemResponseList = new ArrayList<>();



        switch (category.getProblemTableName()) {
            case NAME_PROBLEM -> {
                for(NameProblem nameProblem : nameProblemRepository.findByCategory(category)) {
                    problemResponseList.add(NameProblemResponse.from(nameProblem));
                }
            }
            case IMAGE_MATCHING_PROBLEM -> {
                for(ImageMatchingProblem imageMatchingProblem : imageMatchingProblemRepository.findByCategory(category)) {
                    problemResponseList.add(ImageMatchingProblemResponse.from(imageMatchingProblem));
                }
            }
            case TEXT_MATCHING_PROBLEM -> {
                for(TextMatchingProblem textMatchingProblem : textMatchingProblemRepository.findByCategory(category)) {
                    problemResponseList.add(TextMatchingProblemResponse.from(textMatchingProblem));
                }
            }
            case SPEAKING_PROBLEM -> {
                for(SpeakingProblem speakingProblem : speakingProblemRepository.findByCategory(category)) {
                    problemResponseList.add(SpeakingProblemResponse.from(speakingProblem));
                }
            }
            case FLUENCY_PROBLEM -> {
                for(FluencyProblem fluencyProblem : fluencyProblemRepository.findByCategory(category)) {
                    problemResponseList.add(FluencyProblemResponse.from(fluencyProblem));
                }
            }
        }
        return problemResponseList;
    }

    public void solveProblem(SolvedProblemRequest solvedProblemRequest) {
        Optional<Category> categoryOpt = categoryRepository.findById(solvedProblemRequest.getCategoryId());
        Category category = categoryOpt.orElseThrow(NoSuchParameterException::new);

        switch (category.getProblemTableName()) {
            case NAME_PROBLEM -> {
                Optional<NameProblem> nameProblemOpt = nameProblemRepository.findById(solvedProblemRequest.getProblemId());
                NameProblem nameProblem = nameProblemOpt.orElseThrow(NoSuchParameterException::new);
                SolvedProblem.of(
                        solvedProblemRequest.isRight(),
                        solvedProblemRequest.getTime(),
                        nameProblem)
            }
            case IMAGE_MATCHING_PROBLEM -> {
                Optional<ImageMatchingProblem>
            }

        }

    }





}
