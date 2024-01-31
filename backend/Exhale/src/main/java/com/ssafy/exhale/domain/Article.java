package com.ssafy.exhale.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;
    private String title;
    private String content;
    private int view;
    private String thumbnail;
    private String nickname;

    @Column(name = "create_time")
    private LocalDateTime createDate;

    @Column(name = "modify_time")
    private LocalDateTime modifyDate;

    @Column(name = "is_delete")
    private Boolean isDelete;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Article of(Long id, String title, String content, int view,
                             String thumbnail, String nickname,
                             LocalDateTime createDate, LocalDateTime modifyDate,
                             Boolean isDelete, Board board, Member member)
    {
        return new Article(id, title, content, view, thumbnail, nickname,
                createDate, modifyDate, isDelete, board, member);
    }
}
