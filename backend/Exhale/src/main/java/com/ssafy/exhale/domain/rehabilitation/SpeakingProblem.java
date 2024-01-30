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
@Table(name = "speaking_problem")
public class SpeakingProblem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "speaking_problem_id")
    private Long id;

    @Column
    private String question;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "is_removed")
    private Boolean isRemoved;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
