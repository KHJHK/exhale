package com.ssafy.exhale.service;

import com.ssafy.exhale.domain.Article;
import com.ssafy.exhale.dto.requestDto.ArticleSearchRequest;
import com.ssafy.exhale.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> getArticleListByBoardId(int boardId, int page){
        PageRequest pageRequest = PageRequest.of(page, 10);
        return articleRepository.findAllByBoardId(boardId, pageRequest);
    }

    public Article getArticle(Long articleId){
        Article article = null;
        if(articleRepository.findById(articleId).isPresent()){
            article = articleRepository.findById(articleId).get();
        }
        return article;
    }

//    public List<Article> search(ArticleSearchRequest articleSearchRequest){
//        return articleRepository.search(articleSearchRequest);
//    }

    public boolean postArticle(Article article){
        Article saved = articleRepository.save(article);
        return true;
    }


}
