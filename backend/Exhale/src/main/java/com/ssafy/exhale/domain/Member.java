package com.ssafy.exhale.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private int memberId;
    @Column(name="login_id")
    private String loginId;
    private String password;
    private String name;
    @Column(name="email_id")
    private String emailId;
    @Column(name="email_domain")
    private String emailDomain;
    private String birth;
    private String nickname;
    @Column(name="profile_img", nullable = true)
    private String profileImg;
    @Column(name="is_active", nullable = true, columnDefinition = "true")
    private boolean isActive;
    @CreatedDate
    @Column(name="create_date") 
    private LocalDateTime createDate;
    @Column(name="withdrawal_date", nullable = true, columnDefinition = "false")
    private LocalDateTime withdrawal_date;
    private String role;



}
