package com.ssafy.exhale.repository;

import com.ssafy.exhale.domain.ArticleFile;
import com.ssafy.exhale.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleFileRepository extends JpaRepository<ArticleFile, Integer> {
}
