package com.ssafy.exhale.controller;

import com.ssafy.exhale.domain.Board;
import com.ssafy.exhale.domain.Member;
import com.ssafy.exhale.dto.logicDto.BoardDto;
import com.ssafy.exhale.dto.logicDto.MemberDto;
import com.ssafy.exhale.dto.requestDto.ArticleRequest;
import com.ssafy.exhale.dto.requestDto.ArticleSearchRequest;
import com.ssafy.exhale.dto.responseDto.ArticleResponse;
import com.ssafy.exhale.repository.BoardRepository;
import com.ssafy.exhale.repository.MemberRepository;
import com.ssafy.exhale.service.ArticleService;
import com.ssafy.exhale.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;
    private final ArticleService articleService;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    //게시판 종류
    @GetMapping
    public List<Board> getBoardList(){
        return boardService.getBoardList();
    }

    //게시판별 게시글 목록
    @GetMapping("/{board_id}")
    public List<ArticleResponse> getArticleList(@PathVariable("board_id") int boardId, @RequestParam("page") int page){
        return articleService.getArticleListByBoardId(boardId, page);
    }

    //검색
    @PostMapping("/search")
    public List<ArticleResponse> search(@RequestBody ArticleSearchRequest articleSearchRequest){
        return articleService.search(articleSearchRequest);
    }

    //게시글 조회
    @Transactional(readOnly = true)
    @GetMapping("/articles/{article_id}")
    public ArticleResponse getArticle(@PathVariable("article_id") Long articleId){
        return articleService.getArticle(articleId);
    }

    //게시글 생성
    @PostMapping("/articles")
    public void postArticle(@RequestBody ArticleRequest articleRequest){
        Board board = boardRepository.getReferenceById(articleRequest.getBoardId());
        Member member = memberRepository.getReferenceById(articleRequest.getMemberId());
        articleService.postArticle(
                articleRequest.toDto(
                        BoardDto.from(board),
                        MemberDto.from(member)
                )
        );
    }

    //게시글 수정
    @PutMapping("/articles/{article_id}")
    public void modifyArticle(@PathVariable("article_id") Long articleId,@RequestBody ArticleRequest articleRequest){
        articleService.modifyArticle(articleId, articleRequest);
    }

    //게시글 삭제처리
    @DeleteMapping("/articles/{article_id}")
    public void deleteArticle(@PathVariable("article_id") Long articleId){
        articleService.deleteArticle(articleId);
    }

}