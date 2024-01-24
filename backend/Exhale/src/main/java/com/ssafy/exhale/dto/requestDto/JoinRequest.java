package com.ssafy.exhale.dto.requestDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JoinRequest {
    private String login_id;
    private String password;
    private String name;
    private String email_id;
    private String email_domain;
    private String birth;
    private String nickname;
}
