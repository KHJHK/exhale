package com.ssafy.exhale.controller;

import com.ssafy.exhale.dto.requestDto.CommentRequest;
import com.ssafy.exhale.dto.responseDto.commonDto.CommonResponse;
import com.ssafy.exhale.service.CommentService;
import com.ssafy.exhale.util.TokenPayloadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final TokenPayloadUtil tokenPayloadUtil;

    @PostMapping
    public ResponseEntity<CommonResponse> postComment(@RequestBody CommentRequest commentRequest){
        commentService.postComment(commentRequest, tokenPayloadUtil.getMemberId());
        return CommonResponse.ok(null);
    }

    @GetMapping("/list/{article_id}")
    public ResponseEntity<CommonResponse> getCommentList(@PathVariable("article_id") Long articleId){
        return CommonResponse.ok(commentService.getCommentListByArticleId(articleId));
    }

    @PutMapping("/{comment_id}")
    public ResponseEntity<CommonResponse> modifyComment(@PathVariable("comment_id") Long commentId, @RequestBody CommentRequest commentRequest){
        commentService.modifyComment(commentId, commentRequest.getContent(), tokenPayloadUtil.getMemberId());
        return CommonResponse.ok(null);
    }

    @DeleteMapping("/{comment_id}")
    public ResponseEntity<CommonResponse> deleteComment(@PathVariable("comment_id") Long commentId){
        commentService.deleteComment(commentId, tokenPayloadUtil.getMemberId());
        return CommonResponse.ok(null);
    }
}