package com.ssafy.exhale.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
public class Comment {
    @Id
    @Column(name = "comment_id")
    private int commentId;
    private String content;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "create_date")
    private boolean createDate;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "comment_id")
    private Comment comment;

}
