package com.ssafy.exhale.dto.responseDto;

import com.ssafy.exhale.dto.logicDto.ArticleDto;
import com.ssafy.exhale.dto.logicDto.CommentDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentResponse {
    Integer id;
    String content;
    boolean isDelete;
    LocalDateTime createDate;
    LocalDateTime modifyDate;
    ArticleDto articleDto;
    //    MemberDto memberDto;
    CommentDto commentDto;
}
