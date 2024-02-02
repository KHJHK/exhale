package com.ssafy.exhale.dto.requestDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.exhale.dto.logicDto.ArticleDto;
import com.ssafy.exhale.dto.logicDto.CommentDto;
import com.ssafy.exhale.dto.logicDto.MemberDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentRequest {
    @JsonProperty("article_id")
    Long articleId;
    String content;
    @JsonProperty("parent_id")
    Long parentId;

    public CommentDto toDto(ArticleDto articleDto,
                               MemberDto memberDto,
                               CommentDto parentCommentDto){
        return CommentDto.of(
                null,
                content,
                false,
                LocalDateTime.now(),
                null,
                articleDto,
                memberDto,
                parentCommentDto
                );

    }
}
