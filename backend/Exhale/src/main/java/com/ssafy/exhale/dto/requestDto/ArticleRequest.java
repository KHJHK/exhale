package com.ssafy.exhale.dto.requestDto;

import com.ssafy.exhale.dto.logicDto.ArticleDto;
import com.ssafy.exhale.dto.logicDto.BoardDto;
import com.ssafy.exhale.dto.logicDto.MemberDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleRequest {
    private String title;
    private String content;
    private String thumbnail;
    private Integer boardId;

    //작성자 정보 부분
    private Integer memberId;
    private String nickname;

    public ArticleDto toDto(String title, String content, String thumbnail,
                                   String nickname, BoardDto boardDto, MemberDto memberDto){
        return ArticleDto.of(
                title,
                content,
                thumbnail,
                nickname,
                boardDto,
                memberDto
        );
    }

}
