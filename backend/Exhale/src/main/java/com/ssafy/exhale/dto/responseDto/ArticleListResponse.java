package com.ssafy.exhale.dto.responseDto;

import com.ssafy.exhale.dto.logicDto.ArticleDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleListResponse {
    Integer id;
    String title;
    int view;
    String nickname;
    LocalDateTime createDate;
    String thumbnail;
//    Integer memberId;

    public static ArticleListResponse of(Integer id, String title, int view, /*Integer memberId,*/ String nickname,
                                         LocalDateTime createDate, String thumbnail)
    {
        return new ArticleListResponse(
                id,
                title,
                view,
                nickname,
                createDate,
                thumbnail
//                memberId,
        );
    }

    public static ArticleListResponse from(ArticleDto articleDto){
        return new ArticleListResponse(
                articleDto.getId(),
                articleDto.getTitle(),
                articleDto.getView(),
                articleDto.getNickname(),
                articleDto.getCreateDate(),
                articleDto.getThumbnail()
//                articleDto.getMemberDto
        );
    }
}
