package com.ssafy.exhale.dto.logicDto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Integer id;
    private String loginId;
    private String password;
    private String name;
    private String emailId;
    private String emailDomain;
    private String birth;
    private String nickname;
    private String profileImg;
    private boolean isDelete;
    private String refreshValue;
    private LocalDateTime createDate;
    private LocalDateTime withdraw_date;
    private String role;
}
