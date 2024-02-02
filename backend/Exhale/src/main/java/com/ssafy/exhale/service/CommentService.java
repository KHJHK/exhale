package com.ssafy.exhale.service;

import com.ssafy.exhale.domain.Article;
import com.ssafy.exhale.domain.Comment;
import com.ssafy.exhale.domain.Member;
import com.ssafy.exhale.dto.logicDto.ArticleDto;
import com.ssafy.exhale.dto.logicDto.CommentDto;
import com.ssafy.exhale.dto.logicDto.MemberDto;
import com.ssafy.exhale.dto.requestDto.CommentRequest;
import com.ssafy.exhale.repository.ArticleRepository;
import com.ssafy.exhale.repository.CommentRepository;
import com.ssafy.exhale.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    public void postComment(CommentRequest commentRequest, Long memberId) {
        Article articleEntity = articleRepository.getReferenceById(
                commentRequest
                .getArticleId()
        );
        Member memberEntity = memberRepository.getReferenceById(memberId);

        Comment commentEntity = null;
        if(commentRequest.getParentId() != null){
            commentEntity = commentRepository.getReferenceById(
                    commentRequest
                    .getParentId()
            );
        }

        CommentDto commentDto = commentRequest.toDto(
                ArticleDto.from(articleEntity),
                MemberDto.from(memberEntity),
                null
        );
        commentRepository.save(commentDto.toEntity(articleEntity, memberEntity, commentEntity));
    }
}
