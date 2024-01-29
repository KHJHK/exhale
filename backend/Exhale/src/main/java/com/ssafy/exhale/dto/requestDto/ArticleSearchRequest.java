package com.ssafy.exhale.dto.requestDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleSearchRequest {
    int boardId;
    String searchType;
    String searchContent;
    int page;
}
