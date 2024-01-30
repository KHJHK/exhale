package com.ssafy.exhale.dto.responseDto.rehabilitationDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.exhale.domain.rehabilitation.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryResponse {
    @JsonProperty("category_id")
    private long categoryId;

    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty("over_time_sec")
    private int overTimeSec;

    public static CategoryResponse from(Category category) {
        return new CategoryResponse(category.getId(), category.getCategoryName(), category.getOverTimeSec());
    }
}
