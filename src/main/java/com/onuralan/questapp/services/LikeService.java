package com.onuralan.questapp.services;


import com.onuralan.questapp.entities.Like;
import com.onuralan.questapp.entities.Post;
import com.onuralan.questapp.entities.User;
import com.onuralan.questapp.repos.LikeRepository;
import com.onuralan.questapp.requests.LikeCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {
    LikeRepository likeRepository;

    UserService userService;

    PostService postService;

    public LikeService(LikeRepository likeRepository,UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Like> getAllLikes(Optional<Long> postId, Optional<Long> userId) {
        if (postId.isPresent() && userId.isPresent()){
            return likeRepository.findByPostIdAndUserId(postId.get(),userId.get());
        } else if (postId.isPresent()) {
            return likeRepository.findByPostId(postId.get());
        } else if (userId.isPresent()) {
            return likeRepository.findByUserId(userId.get());
        }else {
            return likeRepository.findAll();
        }
    }

    public Like getOneLike(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public void deleteLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }

    public Like CreateLike(LikeCreateRequest likeCreateRequest) {
        User user = userService.getOneUser(likeCreateRequest.getUserId());
        Post post = postService.getOnePostByPostId(likeCreateRequest.getPostId());
        if (user != null && post != null) {
            Like toSave = new Like();
            toSave.setUser(user);
            toSave.setPost(post);
            toSave.setId(likeCreateRequest.getId());
            return likeRepository.save(toSave);
        }else
            return null;

    }
}
