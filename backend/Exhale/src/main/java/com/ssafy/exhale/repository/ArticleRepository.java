package com.ssafy.exhale.repository;

import com.ssafy.exhale.domain.Article;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {
    List<Article> findAllByBoardId(int boardId, PageRequest page);  //custom해서 전달 데이터 종류 줄이기
}
