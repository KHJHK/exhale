package com.ssafy.exhale.service;

import com.ssafy.exhale.domain.rehabilitation.*;
import com.ssafy.exhale.domain.rehabilitation.ProblemTable;
import com.ssafy.exhale.dto.logicDto.rehabilitationDto.*;
import com.ssafy.exhale.domain.rehabilitation.CategoryName;
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
            courseResponseList.add(CourseResponse.from(CourseDto.from(course)));
        }
        return courseResponseList;
    }

    public List<CategoryResponse> getCategory(long courseId) {
        Optional<Course> courseOpt = courseRepository.findByIdAndIsRemoved(courseId, false);
        Course course = courseOpt.orElseThrow(NoSuchParameterException::new);

        List<CategoryResponse> categoryResponsesList = new ArrayList<>();
        for(Category category : categoryRepository.findByCourse(course)) {
            categoryResponsesList.add(CategoryResponse.from(CategoryDto.from(category)));
        }
        return categoryResponsesList;
    }

    public List<? extends ProblemResponse> getProblemList(long categoryId) {
        Optional<Category> categoryOpt = categoryRepository.findById(categoryId);
        Category category = categoryOpt.orElseThrow(NoSuchParameterException::new);

        switch (category.getProblemTableName()) {
            case NAME_PROBLEM -> {
                List<NameProblemResponse> responseList = new ArrayList<>();
                for(NameProblem nameProblem : nameProblemRepository.findByCategory(category)) {
                    responseList.add(NameProblemResponse.from(NameProblemDto.from(nameProblem)));
                    return responseList;
                }
            }
            case IMAGE_MATCHING_PROBLEM -> {
                List<ImageMatchingProblemResponse> responseList = new ArrayList<>();
                for(ImageMatchingProblem imageMatchingProblem : imageMatchingProblemRepository.findByCategory(category)) {
                    responseList.add(ImageMatchingProblemResponse.from(ImageMatchingProblemDto.from(imageMatchingProblem)));
                }
                return responseList;
            }
            case TEXT_MATCHING_PROBLEM -> {
                List<TextMatchingProblemResponse> responseList = new ArrayList<>();
                for(TextMatchingProblem textMatchingProblem : textMatchingProblemRepository.findByCategory(category)) {
                    responseList.add(TextMatchingProblemResponse.from(TextMatchingProblemDto.from(textMatchingProblem)));
                }
                return responseList;
            }
            case SPEAKING_PROBLEM -> {
                List<SpeakingProblemResponse> responseList = new ArrayList<>();
                for(SpeakingProblem speakingProblem : speakingProblemRepository.findByCategory(category)) {
                    responseList.add(SpeakingProblemResponse.from(SpeakingProblemDto.from(speakingProblem)));
                }
                return responseList;
            }
            case FLUENCY_PROBLEM -> {

            }
        }
    }
}
