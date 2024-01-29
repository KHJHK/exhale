package com.ssafy.exhale.repository;

import com.ssafy.exhale.domain.Article;
import com.ssafy.exhale.dto.requestDto.ArticleSearchRequest;
import com.ssafy.exhale.dto.responseDto.ArticleListResponse;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ArticleRepositoryCustom {
    List<Article> search(ArticleSearchRequest articleSearchRequest);
}
