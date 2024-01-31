package com.ssafy.exhale.dto.responseDto;

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
public class CommentResponse {
    Long id;
    String content;
    Boolean isDelete;
    LocalDateTime createDate;
    LocalDateTime modifyDate;
    ArticleDto articleDto;
    MemberDto memberDto;
    CommentDto parentCommentDto;
}
