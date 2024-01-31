package com.ssafy.exhale.domain;

import com.ssafy.exhale.dto.requestDto.MemberRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column(name = "login_id")
    private String loginId;
    private String password;
    private String name;
    @Column(name = "email_id")
    private String emailId;
    @Column(name = "email_domain")
    private String emailDomain;
    private String birth;
    private String nickname;
    @Column(name = "profile_img", nullable = true)
    private String profileImg;
    @Column(name = "withdraw", nullable = true, columnDefinition = "TINYINT(1) default 0")
    private Boolean withdraw;
    @Column(name = "refresh_value", nullable = true)
    private String refreshValue;
    @CreatedDate
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "withdraw_date", nullable = true)
    private LocalDateTime withdrawDate;
    private String role;

    public static Member of(Long id, String loginId, String password,
                            String name, String emailId, String emailDomain,
                            String birth, String nicknam, String profileImg, Boolean withhdraw,
                            LocalDateTime createDate, LocalDateTime withdrawDate, String role){
        return new Member(id, loginId, password, name, emailId, emailDomain,
                birth, nicknam, profileImg, withhdraw, null, createDate, withdrawDate, role);
    }

    public void updateWithdraw(boolean withdrawal) {
        if (withdrawal) this.withdraw = true;
        else this.withdraw = false;
    }

    public void updateJoin(MemberRequest memberRequest, String password) {
        this.name = memberRequest.getName();
        this.birth = memberRequest.getBirth();
        this.emailDomain = memberRequest.getEmailDomain();
        this.emailId = memberRequest.getEmailId();
        this.loginId = memberRequest.getLoginId();
        this.password = password;
        this.role = "ROLE_USER";
    }

    public void updateRefreshValue(String value) {
        this.refreshValue = value;
    }

    public void capsulePayload(String loginId, long memberId, String role) {
        this.loginId = loginId;
        this.id = memberId;
        this.role = role;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}

