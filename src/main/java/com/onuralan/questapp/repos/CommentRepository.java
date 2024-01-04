package com.onuralan.questapp.repos;

import com.onuralan.questapp.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostIdAndUserId(Long postId, Long userId);

    List<Comment> findByPostId(Long postId);

    List<Comment> findByUserId(Long userId);
}
