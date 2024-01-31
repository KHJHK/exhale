package com.ssafy.exhale.dto.responseDto;

import com.ssafy.exhale.dto.logicDto.CommentDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentResponse {
    Long id;
    String content;
    Boolean isDelete;
    LocalDateTime createDate;
    LocalDateTime modifyDate;
    Long articleId;
    Long memberId;
    Long parentCommentId;

    public static CommentResponse of(Long id, String content, Boolean isDelete,
                                     LocalDateTime createDate, LocalDateTime modifyDate,
                                     Long articleId, Long memberId, Long parentCommentId){
        return new CommentResponse(id, content, isDelete, createDate, modifyDate,
                articleId, memberId, parentCommentId);
    }

    public static CommentResponse from(CommentDto dto){
        return new CommentResponse(
                dto.getId(),
                dto.getContent(),
                dto.getIsDelete(),
                dto.getCreateDate(),
                dto.getModifyDate(),
                dto.getArticleDto().getId(),
                dto.getMemberDto().getId(),
                dto.getParentCommentDto().getId()
        );
    }
}
