package com.ssafy.exhale.controller;

import com.ssafy.exhale.dto.requestDto.CommentRequest;
import com.ssafy.exhale.dto.responseDto.CommentResponse;
import com.ssafy.exhale.service.CommentService;
import com.ssafy.exhale.util.TokenPayloadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final TokenPayloadUtil tokenPayloadUtil;

    @PostMapping
    public void postComment(@RequestBody CommentRequest commentRequest){
        commentService.postComment(commentRequest, tokenPayloadUtil.getMemberId());
    }

    @GetMapping("/{article_id}")
    public List<CommentResponse> getCommentList(@PathVariable("article_id") Long articleId){
        return commentService.getCommentListByArticleId(articleId);
    }
//
//    @PutMapping
//
//    @PutMapping
}