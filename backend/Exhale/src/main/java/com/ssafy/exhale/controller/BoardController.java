package com.ssafy.exhale.controller;

import com.ssafy.exhale.domain.Board;
import com.ssafy.exhale.dto.requestDto.ArticleRequest;
import com.ssafy.exhale.dto.requestDto.ArticleSearchRequest;
import com.ssafy.exhale.dto.responseDto.ArticleResponse;
import com.ssafy.exhale.dto.responseDto.commonDto.CommonResponse;
import com.ssafy.exhale.service.ArticleService;
import com.ssafy.exhale.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;
    private final ArticleService articleService;

    //게시판 종류
    @GetMapping
    public ResponseEntity<CommonResponse> getBoardList(){
        return CommonResponse.ok(boardService.getBoardList());
    }

    //게시판별 게시글 목록
    @GetMapping("/{board_id}")
    public ResponseEntity<CommonResponse> getArticleList(@PathVariable("board_id") int boardId, @RequestParam("page") int page) {
        return CommonResponse.ok(articleService.getArticleListByBoardId(boardId, page));
    }

    //검색
    @PostMapping("/search")
    public ResponseEntity<CommonResponse> search(@RequestBody ArticleSearchRequest articleSearchRequest){
        return CommonResponse.ok(articleService.search(articleSearchRequest));
    }

    //게시글 조회
    @Transactional(readOnly = true)
    @GetMapping("/articles/{article_id}")
    public ResponseEntity<CommonResponse> getArticle(@PathVariable("article_id") Long articleId){
        return CommonResponse.ok(articleService.getArticle(articleId));
    }

    //게시글 생성
    @PostMapping("/articles")
    public ResponseEntity<CommonResponse> postArticle(@RequestBody ArticleRequest articleRequest){
        articleService.postArticle(articleRequest);
        return CommonResponse.ok(null);
    }

    //게시글 수정
    @PutMapping("/articles/{article_id}")
    public ResponseEntity<CommonResponse> modifyArticle(@PathVariable("article_id") Long articleId,@RequestBody ArticleRequest articleRequest){
        articleService.modifyArticle(articleId, articleRequest);
        return CommonResponse.ok(null);
    }

    //게시글 삭제처리
    @DeleteMapping("/articles/{article_id}")
    public ResponseEntity<CommonResponse> deleteArticle(@PathVariable("article_id") Long articleId){
        articleService.deleteArticle(articleId);
        return CommonResponse.ok(null);
    }
}