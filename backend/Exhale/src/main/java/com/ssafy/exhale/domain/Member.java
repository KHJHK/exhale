package com.ssafy.exhale.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private long memberId;
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
    @Column(name="profile_img")
    private String profileImg;
    @Column(name="is_active")
    private boolean isActive;
    @CreatedDate
    @Column(name="create_date")
    private LocalDateTime createDate;
    @CreatedDate
    @Column(name="withdrawal_date")
    private LocalDateTime withdrawal_date;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;



}
