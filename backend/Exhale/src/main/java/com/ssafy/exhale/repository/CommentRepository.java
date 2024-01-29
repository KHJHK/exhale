package com.ssafy.exhale.repository;

import com.ssafy.exhale.domain.Comment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByArticleId(int articleId);
}