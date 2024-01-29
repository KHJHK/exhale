package com.ssafy.exhale.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Integer id;
    private String title;
    private String content;

    @Column(name = "is_hidden")
    private Boolean isHidden;
    private int view;
    private String thumbnail;
    private String nickname;

    @Column(name = "withdraw")
    private boolean withdraw;

    @Column(name = "create_time")
    @Temporal(TemporalType.DATE)
    private Date createTime;

    @Column(name = "modify_time")
    @Temporal(TemporalType.DATE)
    private Date modifyDate;

    @Column(name = "is_delete")
    private boolean isDelete;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
