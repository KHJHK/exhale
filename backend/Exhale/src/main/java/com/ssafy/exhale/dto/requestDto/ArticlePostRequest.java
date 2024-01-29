package com.ssafy.exhale.dto.requestDto;

import com.ssafy.exhale.domain.Article;
import com.ssafy.exhale.domain.Board;
import com.ssafy.exhale.domain.Member;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticlePostRequest {
    private String title;
    private String content;
    private Boolean isHidden;
    private String thumbnail;
    private String nickname;
    private Board board;
    private Member member;

    public Article toEntity(){
        //todo : Article Entity로 변환시켜주는 메서드 작성
        return new Article();
    }

}
