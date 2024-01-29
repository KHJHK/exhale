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
@Table(name = "image_matching_problem")
public class ImageMatchingProblem {
    @Id
    @Column(name = "image_matching_problem_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageMatchingProblemId;

    @Column
    private String question;

    @Column(name = "option_image1")
    private String optionImage1;

    @Column(name = "option_image2")
    private String optionImage2;

    @Column(name = "option_image3")
    private String optionImage3;

    @Column(name = "option_image4")
    private String optionImage4;

    @Column
    private Integer answer;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "is_removed")
    private Boolean isRemoved;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
