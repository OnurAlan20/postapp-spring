package com.onuralan.questapp.services;

import com.onuralan.questapp.entities.Comment;
import com.onuralan.questapp.entities.Post;
import com.onuralan.questapp.entities.User;
import com.onuralan.questapp.repos.CommentRepository;
import com.onuralan.questapp.requests.CommentCreateRequest;
import com.onuralan.questapp.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UserService userService;

    private PostService postService;


    public CommentService(CommentRepository commentRepository,PostService postService, UserService userService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
        this. userService = userService;
    }


    public List<Comment> getAllComments(Optional<Long> postId, Optional<Long> userId) {
        if (postId.isPresent() && userId.isPresent()){
            return commentRepository.findByPostIdAndUserId(postId.get(),userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        }else {
            return commentRepository.findAll();
        }
    }

    public Comment getOneComment(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public Comment updateOneComment(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()){
            Comment toUpdate = comment.get();
            toUpdate.setText(commentUpdateRequest.getText());
            return commentRepository.save(toUpdate);
        }
        return null;
    }

    public Comment CreateComment(CommentCreateRequest commentCreateRequest) {
        User user = userService.getOneUser(commentCreateRequest.getUserId());
        Post post = postService.getOnePostByPostId(commentCreateRequest.getPostId());
        if (user != null && post != null){
            Comment comment = new Comment();
            comment.setText(commentCreateRequest.getText());
            comment.setUser(user);
            comment.setPost(post);
            comment.setId(commentCreateRequest.getId());
            return commentRepository.save(comment);
        }else
            return null;

    }
}
