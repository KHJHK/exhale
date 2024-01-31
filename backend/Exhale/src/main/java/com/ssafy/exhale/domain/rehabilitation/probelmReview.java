package com.ssafy.exhale.domain.rehabilitation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "problem_review")
public class probelmReview {
    @Id
    @Column(name = "problem_review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "name_problem_id")
    private NameProblem nameProblem;

    @ManyToOne
    @JoinColumn(name = "image_matching_problem_id")
    private ImageMatchingProblem imageMatchingProblem;

    @ManyToOne
    @JoinColumn(name = "text_matching_problem_id")
    private TextMatchingProblem textMatchingProblem;

    @ManyToOne
    @JoinColumn(name = "speaking_problem_id")
    private SpeakingProblem speakingProblem;

    @ManyToOne
    @JoinColumn(name = "fluency_problem_id")
    private FluencyProblem fluencyProblem;
}
