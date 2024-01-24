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
@Table(name = "article_file")
public class ArticleFile {
    @Id
    @Column(name = "article_file_id")
    private int articleFileId;

    @Column(name = "origin_name")
    private String originName;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
}
