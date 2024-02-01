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
@Table(name = "solved_problem")
public class SolvedProblem {
    @Id
    @Column(name = "solved_logging_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_correct")
    private Boolean isCorrect;

    @Column(name = "solve_time")
    private Integer solveTime;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "is_removed")
    private Boolean isRemoved;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
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

    public static SolvedProblem of(boolean isCorrect, int solveTime, Member member, NameProblem nameProblem,
                                   ImageMatchingProblem imageMatchingProblem, TextMatchingProblem textMatchingProblem,
                                   SpeakingProblem speakingProblem, FluencyProblem fluencyProblem) {

        return new SolvedProblem(
                null,
                isCorrect,
                solveTime,
                null,
                null,
                member,
                nameProblem,
                imageMatchingProblem,
                textMatchingProblem,
                speakingProblem,
                fluencyProblem
        );
    }
}
