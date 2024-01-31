package com.ssafy.exhale.repository;

import com.ssafy.exhale.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByArticleId(int articleId);
}
