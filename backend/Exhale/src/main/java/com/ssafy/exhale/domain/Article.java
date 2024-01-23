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
    @Column(name = "article_id")
    private int articleId;
    private String title;

    @Column(name = "is_hidden")
    private Boolean isHidden;
    private int view;
    private String thumbnail;
    private String nickname;

    @Column(name = "is_withdrawal")
    private boolean isWithdrawal;

    @Column(name = "create_date")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(name = "modify_date")
    @Temporal(TemporalType.DATE)
    private Date modifyDate;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "category")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    @OrderBy("createDate desc")
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<ArticleFile> ArticleFiles;
}
