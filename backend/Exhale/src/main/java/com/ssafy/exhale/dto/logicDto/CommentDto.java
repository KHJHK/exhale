package com.ssafy.exhale.dto.logicDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    Integer id;
    String content;
    boolean isDelete;
    LocalDateTime createDate;
    LocalDateTime modifyDate;
    ArticleDto articleDto;
//    MemberDto memberDto;
    CommentDto commentDto;
}
