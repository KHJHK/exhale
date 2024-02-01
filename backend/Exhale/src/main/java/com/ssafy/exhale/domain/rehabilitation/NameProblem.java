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
@DiscriminatorValue("A")
@Table(name = "name_problem")
public class NameProblem extends Problem {
    @Column(name = "question_image")
    private String questionImage;

    @Column
    private String answser;

    @Column
    private String hint;
}
