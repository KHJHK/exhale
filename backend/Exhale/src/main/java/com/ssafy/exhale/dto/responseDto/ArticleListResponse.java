package com.ssafy.exhale.dto.responseDto;

import com.ssafy.exhale.dto.logicDto.ArticleDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleListResponse {
    Long id;
    String title;
    int view;
    String nickname;
    LocalDateTime createDate;
    String thumbnail;
    Long memberId;

    public static ArticleListResponse of(Long id, String title, int view, String nickname,
                                         LocalDateTime createDate, String thumbnail, Long memberId)
    {
        return new ArticleListResponse(
                id,
                title,
                view,
                nickname,
                createDate,
                thumbnail,
                memberId
        );
    }

    public static ArticleListResponse from(ArticleDto articleDto){
        return new ArticleListResponse(
                articleDto.getId(),
                articleDto.getTitle(),
                articleDto.getView(),
                articleDto.getNickname(),
                articleDto.getCreateDate(),
                articleDto.getThumbnail(),
                articleDto.getMemberDto().getId()
        );
    }
}
