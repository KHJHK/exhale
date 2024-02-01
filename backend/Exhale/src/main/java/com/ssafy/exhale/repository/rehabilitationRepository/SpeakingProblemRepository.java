package com.ssafy.exhale.repository.rehabilitationRepository;

import com.ssafy.exhale.domain.rehabilitation.Category;
import com.ssafy.exhale.domain.rehabilitation.SpeakingProblem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpeakingProblemRepository extends JpaRepository<SpeakingProblem, Long> {
    List<SpeakingProblem> findByCategory(Category category);
}
