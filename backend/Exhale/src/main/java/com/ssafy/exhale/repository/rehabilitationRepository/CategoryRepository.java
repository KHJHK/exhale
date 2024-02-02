package com.ssafy.exhale.repository.rehabilitationRepository;

import com.ssafy.exhale.domain.rehabilitation.Category;
import com.ssafy.exhale.domain.rehabilitation.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByCourseAndIsRemoved(Course course, boolean isRemoved);
}
