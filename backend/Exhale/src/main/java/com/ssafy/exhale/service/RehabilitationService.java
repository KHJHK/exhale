package com.ssafy.exhale.service;

import com.ssafy.exhale.domain.Member;
import com.ssafy.exhale.domain.rehabilitation.*;
import com.ssafy.exhale.dto.requestDto.SolvedProblemRequest;
import com.ssafy.exhale.dto.responseDto.rehabilitationDto.*;
import com.ssafy.exhale.exception.handler.NoSuchParameterException;
import com.ssafy.exhale.repository.MemberRepository;
import com.ssafy.exhale.repository.rehabilitationRepository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RehabilitationService {

    private final MemberRepository memberRepository;
    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;
    private final ProblemRepository<Problem> problemRepository;
    private final SolvedProblemRepository solvedProblemRepository;


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
        for(Category category : categoryRepository.findByCourseAndIsRemoved(course, false)) {
            categoryResponsesList.add(CategoryResponse.from(category));
        }
        return categoryResponsesList;
    }

    public List<ProblemResponse> getProblemList(long categoryId) {
        Optional<Category> categoryOpt = categoryRepository.findById(categoryId);
        Category category = categoryOpt.orElseThrow(NoSuchParameterException::new);

        List<ProblemResponse> problemResponseList = new ArrayList<>();
        for(Problem problem : problemRepository.findByCategoryAndIsRemoved(category, false)) {
            problemResponseList.add(ProblemResponse.from(problem));
        }
        return problemResponseList;
    }

    @Transactional
    public void solveProblem(SolvedProblemRequest solvedProblemRequest, String loginId) {
        System.out.println("테스트");
        Optional<Problem> problemOpt = problemRepository.findByIdAndIsRemoved(solvedProblemRequest.getProblemId(), false);
        Problem problem = problemOpt.orElseThrow(NoSuchParameterException::new);

        Optional<Member> memberOpt = memberRepository.findByLoginIdAndWithdrawIs(loginId, false);
        Member member = memberOpt.orElseThrow(NoSuchParameterException::new);

        SolvedProblem solvedProblem = SolvedProblem.of(
                solvedProblemRequest.isRight(),
                solvedProblemRequest.getTime(),
                member,
                problem);
        solvedProblemRepository.save(solvedProblem);
    }
}
