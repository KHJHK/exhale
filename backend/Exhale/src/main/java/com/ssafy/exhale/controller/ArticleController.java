package com.ssafy.exhale.controller;

import com.ssafy.exhale.dto.requestDto.ArticleRequest;
import com.ssafy.exhale.dto.responseDto.commonDto.CommonResponse;
import com.ssafy.exhale.service.ArticleService;
import com.ssafy.exhale.util.TokenPayloadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;
    private final TokenPayloadUtil tokenPayloadUtil;

    //게시글 조회
    @Transactional(readOnly = true)
    @GetMapping("/{article_id}")
    public ResponseEntity<CommonResponse> getArticle(@PathVariable("article_id") Long articleId){
        return CommonResponse.ok(articleService.getArticle(articleId));
    }

    //게시글 생성
    @PostMapping
    public ResponseEntity<CommonResponse> postArticle(@RequestBody ArticleRequest articleRequest){
        articleService.postArticle(articleRequest, tokenPayloadUtil.getMemberId());
        return CommonResponse.ok(null);
    }

    //게시글 수정
    @PutMapping("/{article_id}")
    public ResponseEntity<CommonResponse> modifyArticle(@PathVariable("article_id") Long articleId,@RequestBody ArticleRequest articleRequest){
        articleService.modifyArticle(articleId, articleRequest, tokenPayloadUtil.getMemberId());
        return CommonResponse.ok(null);
    }

    //게시글 삭제처리
    @DeleteMapping("/{article_id}")
    public ResponseEntity<CommonResponse> deleteArticle(@PathVariable("article_id") Long articleId){
        articleService.deleteArticle(articleId, tokenPayloadUtil.getMemberId());
        return CommonResponse.ok(null);
    }

    @PostMapping("/image/{article_id}")
    public ResponseEntity<CommonResponse> saveImage(@PathVariable("article_id") Long articleId, @RequestParam(value = "file") MultipartFile file){
        System.out.println("file = " + file.getOriginalFilename());
        String imageURL = articleService.saveImage(file, articleId);
        return CommonResponse.ok(imageURL);
    }

    @DeleteMapping("/image/{article_file_id}")
    public ResponseEntity<CommonResponse> deleteImage(@PathVariable("article_file_id") Long articleFileId){
        articleService.deleteImage(articleFileId);
        return CommonResponse.ok(null);
    }
}
