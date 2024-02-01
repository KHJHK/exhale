package com.ssafy.exhale.service;

import com.ssafy.exhale.domain.Article;
import com.ssafy.exhale.dto.logicDto.ArticleDto;
import com.ssafy.exhale.dto.requestDto.ArticleRequest;
import com.ssafy.exhale.dto.requestDto.ArticleSearchRequest;
import com.ssafy.exhale.dto.responseDto.ArticleResponse;
import com.ssafy.exhale.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final int PAGE_SIZE = 10;

    public List<ArticleResponse> getArticleListByBoardId(Integer boardId, int page){
        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE);
        List<Article> articleEntityList = articleRepository.findAllByBoardId(boardId, pageRequest);

        return articleEntityList.stream()
                .map(article -> {
                    ArticleDto articleDto = ArticleDto.from(article);
                    return ArticleResponse.from(articleDto);
                })
                .collect(Collectors.toList());
    }

    public ArticleResponse getArticle(Long articleId){
        Article article = null;
        if(articleRepository.findById(articleId).isPresent()){
            article = articleRepository.findById(articleId).get();
        }else{
            //예외 처리 임시 코드. 나중에 예외 처리 로직 추가 후 삭제하기
            ArticleResponse tempError = new ArticleResponse();
            tempError.setTitle("null일때 주는 객체");
            return tempError;
        }

        return ArticleResponse.from(ArticleDto.from(article));
    }
    public void postArticle(ArticleDto articleDto){
        Article article = articleDto.toEntity(
                articleDto.getBoardDto().toEntity(),
                articleDto.getMemberDto().toEntity()
        );
        articleRepository.save(article);
    }

    public void modifyArticle(Long articleId, ArticleRequest articleRequest){
        if(articleRepository.findById(articleId).isPresent()){
            Article originalArticle = articleRepository.findById(articleId).get();
            ArticleDto originalArticleDto = ArticleDto.from(originalArticle);

            originalArticleDto.setId(articleId);
            originalArticleDto.setModifyDate(LocalDateTime.now());
            originalArticleDto.setTitle(articleRequest.getTitle());
            originalArticleDto.setContent(articleRequest.getContent());
            originalArticleDto.setThumbnail(articleRequest.getThumbnail());


            Article modifyArticle = originalArticleDto.toEntity(
                    originalArticleDto.getBoardDto().toEntity(),
                    originalArticleDto.getMemberDto().toEntity()
            );
            articleRepository.save(modifyArticle);
        }
    }

    public void deleteArticle(Long articleId){
        if(articleRepository.findById(articleId).isPresent()){
            Article originalArticle = articleRepository.findById(articleId).get();
            ArticleDto articleDto = ArticleDto.from(originalArticle);
            articleDto.setIsDelete(true);
            Article deleteArticle = articleDto.toEntity(
                    articleDto.getBoardDto().toEntity(),
                    articleDto.getMemberDto().toEntity()
            );
            articleRepository.save(deleteArticle);
        }
        //못찾으면 에러
    }

    public List<ArticleResponse> search(ArticleSearchRequest searchRequest){
        PageRequest pageRequest = PageRequest.of(searchRequest.getPage(), PAGE_SIZE);
        List<Article> articleEntityList = articleRepository.search(searchRequest, pageRequest);
        return articleEntityList.stream()
                .map(article -> {
                    ArticleDto articleDto = ArticleDto.from(article);
                    return ArticleResponse.from(articleDto);
                })
                .collect(Collectors.toList());
    }
}
