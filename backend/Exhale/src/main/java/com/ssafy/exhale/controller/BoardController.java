package com.ssafy.exhale.controller;

import com.ssafy.exhale.dto.requestDto.ArticleSearchRequest;
import com.ssafy.exhale.dto.responseDto.commonDto.CommonResponse;
import com.ssafy.exhale.service.ArticleService;
import com.ssafy.exhale.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}