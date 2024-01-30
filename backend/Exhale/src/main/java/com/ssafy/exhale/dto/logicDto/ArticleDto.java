package com.ssafy.exhale.dto.logicDto;

import com.ssafy.exhale.domain.Article;
import com.ssafy.exhale.domain.Board;
import com.ssafy.exhale.domain.Member;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    Integer id;
    String title;
    String content;
    int view;
    String thumbnail;
    String nickname;
    BoardDto board;
//    MemberDto memberDto;

    public static ArticleDto of(Integer id, String title, String content, int view,
                                String thumbnail, String nickname, BoardDto boardDto /*,MemberDto memberDto*/)
    {
        return new ArticleDto(id, title, content, view,thumbnail, nickname, boardDto  /*,memberDto*/);
    }

    public static ArticleDto of(String title, String content,
                                String thumbnail, String nickname, BoardDto boardDto /*,MemberDto memberDto*/)
    {
        return new ArticleDto(null, title, content, 0, thumbnail, nickname, boardDto /*,memberDto*/);
    }

    public Article toEntity(Board board, Member member){
        return Article.of(
                id,
                title,
                content,
                view,
                thumbnail,
                nickname,
                board,
                member
        );
    }
    public static ArticleDto from(Article entity){
        return ArticleDto.of(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getView(),
                entity.getThumbnail(),
                entity.getNickname(),
                BoardDto.from(entity.getBoard())
        );
    }
}
