package com.ssafy.exhale.repository.rehabilitationRepository;

import com.ssafy.exhale.domain.rehabilitation.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByCourseId(long courseId);
}
