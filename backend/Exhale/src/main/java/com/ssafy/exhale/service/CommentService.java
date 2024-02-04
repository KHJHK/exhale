package com.ssafy.exhale.service;

import com.ssafy.exhale.domain.Article;
import com.ssafy.exhale.domain.Comment;
import com.ssafy.exhale.domain.Member;
import com.ssafy.exhale.dto.logicDto.ArticleDto;
import com.ssafy.exhale.dto.logicDto.CommentDto;
import com.ssafy.exhale.dto.logicDto.MemberDto;
import com.ssafy.exhale.dto.requestDto.CommentRequest;
import com.ssafy.exhale.dto.responseDto.CommentResponse;
import com.ssafy.exhale.repository.ArticleRepository;
import com.ssafy.exhale.repository.CommentRepository;
import com.ssafy.exhale.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public List<CommentResponse> getCommentListByArticleId(Long articleId){
        List<Comment> commentEntityList = commentRepository.getParentCommentListByArticleId(articleId);

        List<CommentDto> commentDtoList = commentEntityList.stream()
                .map(comment -> {
                    List<CommentDto> childDtoList = comment.getChildCommentList().stream()
                            .map(child -> CommentDto.from(child, null))
                            .toList();

                    return CommentDto.from(comment, childDtoList);
                })
                .toList();

        return commentDtoList.stream()
                .map(commentDto -> {
                    List<CommentResponse> childResponseList = commentDto.getChildCommentDtoList().stream()
                            .map(childDto -> CommentResponse.from(childDto, null))
                            .toList();

                    return CommentResponse.from(commentDto, childResponseList);
                })
                .toList();
    }

    public void modifyComment(Long commentId, String content, Long memberId){
        System.out.println("여기들어왔니???");
        if(commentRepository.findById(commentId).isPresent()){
            Comment comment = commentRepository.findById(commentId).get();
            if(!memberId.equals(comment.getMember().getId())){
                //수정 권한 없음
                return;
            }
            CommentDto commentDto = CommentDto.from(comment, null);
            commentDto.setContent(content);

            Article article = articleRepository.getReferenceById(commentDto.getArticleDto().getId());
            Member member = memberRepository.getReferenceById(commentDto.getMemberDto().getId());
            Comment modifyComment = commentDto.toEntity(article, member, null);
            commentRepository.save(modifyComment);
        }else{
            System.out.println("error");
            //error 메세지 출력
        }
    }
}
