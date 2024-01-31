package com.ssafy.exhale.dto.responseDto;

import com.ssafy.exhale.dto.logicDto.ArticleDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleResponse {
    Long id;
    String title;
    String thumbnail;
    String content;
    Long memberId;
    String nickname;
    int view;
    LocalDateTime createDate;
    LocalDateTime modifyDate;

    public static ArticleResponse of(Long id, String title, String thumbnail,
                                     String content, Long memberId, String nickname, int view,
                                     LocalDateTime createDate, LocalDateTime modifyDate)
    {
        return new ArticleResponse(id, title, thumbnail, content, memberId, nickname, view, createDate, modifyDate);
    }

    public static ArticleResponse from(ArticleDto dto){
        return new ArticleResponse(
                dto.getId(),
                dto.getTitle(),
                dto.getThumbnail(),
                dto.getContent(),
                dto.getMemberDto().getId(),
                dto.getNickname(),
                dto.getView(),
                dto.getCreateDate(),
                dto.getModifyDate()
        );
    }
}
