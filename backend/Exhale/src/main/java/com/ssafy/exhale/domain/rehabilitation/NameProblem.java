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
@Table(name = "name_problem")
public class NameProblem {
    @Id
    @Column(name = "name_problem_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nameProblemId;

    @Column(name = "question_image")
    private String questionImage;

    @Column
    private String answser;

    @Column
    private String hint;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "is_removed")
    private Boolean isRemoved;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
