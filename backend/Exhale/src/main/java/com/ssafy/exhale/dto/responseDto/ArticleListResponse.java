package com.ssafy.exhale.dto.responseDto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleListResponse {
    int Id;
    String title;
    int view;
    boolean isHidden;
    int memberId;
    String nickname;
    Date createDate;
}
