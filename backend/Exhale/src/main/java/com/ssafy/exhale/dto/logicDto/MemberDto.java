package com.ssafy.exhale.dto.logicDto;

import com.ssafy.exhale.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String loginId;
    private String password;
    private String name;
    private String emailId;
    private String emailDomain;
    private String birth;
    private String nickname;
    private String profileImg;
    private Boolean withdraw;
    private LocalDateTime createDate;
    private LocalDateTime withdrawDate;
    private String role;

    public static MemberDto of(Long id, String loginId, String password,
                               String name, String emailId, String emailDomain,
                               String birth, String nickname, String profileImg, Boolean withdraw,
                               LocalDateTime createDate, LocalDateTime withdrawDate, String role)
    {
        return new MemberDto(
                id,
                loginId,
                password,
                name,
                emailId,
                emailDomain,
                birth,
                nickname,
                profileImg,
                withdraw,
                createDate,
                withdrawDate,
                role
        );
    }

    public static MemberDto from(Member entity){
        return new MemberDto(
                entity.getId(),
                entity.getLoginId(),
                entity.getPassword(),
                entity.getName(),
                entity.getEmailId(),
                entity.getEmailDomain(),
                entity.getBirth(),
                entity.getNickname(),
                entity.getProfileImg(),
                entity.getWithdraw(),
                entity.getCreateDate(),
                entity.getWithdrawDate(),
                entity.getRole()
        );
    }

    public Member toEntity(MemberDto dto){
        return Member.of(
                dto.getId(),
                dto.getLoginId(),
                dto.getPassword(),
                dto.getName(),
                dto.getEmailId(),
                dto.getEmailDomain(),
                dto.getBirth(),
                dto.getNickname(),
                dto.getProfileImg(),
                dto.getWithdraw(),
                dto.getCreateDate(),
                dto.getWithdrawDate(),
                dto.getRole()
        );
    }
}
