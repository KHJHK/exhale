package com.ssafy.exhale.dto.requestDto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("board_id")
    private Integer boardId;

    public ArticleDto toDto(BoardDto boardDto, MemberDto memberDto){
        return ArticleDto.of(
                title,
                content,
                thumbnail,
                memberDto.getNickname(),
                boardDto,
                memberDto
        );
    }

}
