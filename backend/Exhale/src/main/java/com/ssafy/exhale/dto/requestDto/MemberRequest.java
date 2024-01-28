package com.ssafy.exhale.dto.requestDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberRequest {
    @JsonProperty("login_id")
    private String loginId;
    private String password;
    private String name;
    private String emailId;
    private String emailDomain;
    private String birth;
    private String nickname;
}
