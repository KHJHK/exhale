package com.ssafy.exhale.dto.requestDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleSearchRequest {
    @NotNull
    @JsonProperty("board_id")
    Integer boardId;

    @NotNull
    @Pattern(regexp = "^(title|content|author)$")
    @JsonProperty("search_type")
    String searchType;

    @NotNull
    @JsonProperty("search_content")
    String searchContent;

    @NotNull
    @Positive
    Integer page;
}
