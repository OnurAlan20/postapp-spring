package com.onuralan.questapp.controllers;


import com.onuralan.questapp.entities.Comment;
import com.onuralan.questapp.entities.Like;
import com.onuralan.questapp.repos.LikeRepository;
import com.onuralan.questapp.requests.CommentCreateRequest;
import com.onuralan.questapp.requests.CommentUpdateRequest;
import com.onuralan.questapp.requests.LikeCreateRequest;
import com.onuralan.questapp.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<Like> getAllLikes(@RequestParam Optional<Long> postId, @RequestParam Optional<Long> userId){
        return likeService.getAllLikes(postId,userId);
    }
    @GetMapping("/{likeId}")
    public Like getOneLike(@PathVariable Long likeId){
        return likeService.getOneLike(likeId);
    }
    @DeleteMapping("/{likeId}")
    public void deleteOneComment(@PathVariable Long likeId){
        likeService.deleteLike(likeId);
    }

    @PostMapping
    public Like CreateComment(@RequestBody LikeCreateRequest likeCreateRequest){
        return likeService.CreateLike(likeCreateRequest);

    }

}
