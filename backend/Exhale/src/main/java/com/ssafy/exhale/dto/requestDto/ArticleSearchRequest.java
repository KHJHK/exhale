package com.ssafy.exhale.dto.requestDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleSearchRequest {
    @JsonProperty("board_id")
    int boardId;
    @JsonProperty("search_type")
    String searchType; // todo 타입 체크 필요
    @JsonProperty("search_content")
    String searchContent;
    int page;
}
