package com.onuralan.questapp.controllers;


import com.onuralan.questapp.entities.Comment;
import com.onuralan.questapp.repos.CommentRepository;
import com.onuralan.questapp.requests.CommentCreateRequest;
import com.onuralan.questapp.requests.CommentUpdateRequest;
import com.onuralan.questapp.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

  CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> postId, @RequestParam Optional<Long> userId){
        return commentService.getAllComments(postId,userId);
    }
    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId){
        return commentService.getOneComment(commentId);
    }
    @DeleteMapping("/{commentId}")
    public  void deleteOneComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
    }
    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest){
        return commentService.updateOneComment(commentId,commentUpdateRequest);
    }
    @PostMapping
    public Comment CreateComment(@RequestBody CommentCreateRequest commentCreateRequest){
        return commentService.CreateComment(commentCreateRequest);

    }



}
