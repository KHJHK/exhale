package com.ssafy.exhale.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
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
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "comment_id")
    private Comment comment;

}
