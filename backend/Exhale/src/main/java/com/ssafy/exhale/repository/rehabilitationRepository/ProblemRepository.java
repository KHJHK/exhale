package com.ssafy.exhale.repository.rehabilitationRepository;

import com.ssafy.exhale.domain.rehabilitation.Category;
import com.ssafy.exhale.domain.rehabilitation.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProblemRepository<T extends Problem> extends JpaRepository<T, Long> {
    List<T> findByCategoryAndIsRemoved(Category category, boolean isRemoved);
    Optional<T> findByIdAndIsRemoved(long id, boolean isRemoved);
}
