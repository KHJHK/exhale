package com.ssafy.exhale.dto.requestDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.exhale.dto.logicDto.MemberDto;
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
    @JsonProperty("email_id")
    private String emailId;
    @JsonProperty("email_domain")
    private String emailDomain;
    private String birth;
    private String nickname;

    public static MemberRequest of(){
        return new MemberRequest();
    }
    public static MemberRequest of(String loginId, String password, String name, String emailId, String emailDomain, String birth, String nickname){
        return new MemberRequest(
                loginId,
                password,
                name,
                emailId,
                emailDomain,
                birth,
                nickname
        );
    }

    public MemberDto toDto(){
        return MemberDto.of(
                null,
                loginId,
                password,
                name,
                emailId,
                emailDomain,
                birth,
                nickname,
                null,
                null,
                null,
                null
        );
    }
}
