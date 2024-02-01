package com.ssafy.exhale.domain.rehabilitation;

import com.ssafy.exhale.domain.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "name_problem_id")
    private NameProblem nameProblem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_matching_problem_id")
    private ImageMatchingProblem imageMatchingProblem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "text_matching_problem_id")
    private TextMatchingProblem textMatchingProblem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "speaking_problem_id")
    private SpeakingProblem speakingProblem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fluency_problem_id")
    private FluencyProblem fluencyProblem;

    public static Review of(Course course, Member member, NameProblem nameProblem,
                         ImageMatchingProblem imageMatchingProblem, TextMatchingProblem textMatchingProblem,
                         SpeakingProblem speakingProblem, FluencyProblem fluencyProblem) {

        return new Review(
                null,
                null,
                course,
                member,
                nameProblem,
                imageMatchingProblem,
                textMatchingProblem,
                speakingProblem,
                fluencyProblem
        );
    }
}
