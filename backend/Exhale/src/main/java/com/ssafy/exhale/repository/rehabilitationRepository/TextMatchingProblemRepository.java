package com.ssafy.exhale.repository.rehabilitationRepository;

import com.ssafy.exhale.domain.rehabilitation.Category;
import com.ssafy.exhale.domain.rehabilitation.NameProblem;
import com.ssafy.exhale.domain.rehabilitation.TextMatchingProblem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TextMatchingProblemRepository extends JpaRepository<TextMatchingProblem, Long> {
    List<TextMatchingProblem> findByCategory(Category category);
}
