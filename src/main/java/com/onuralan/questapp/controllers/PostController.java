package com.onuralan.questapp.controllers;


import com.onuralan.questapp.entities.Post;
import com.onuralan.questapp.requests.PostCreateRequest;
import com.onuralan.questapp.requests.PostUpdateRequest;
import com.onuralan.questapp.services.PostService;
import com.onuralan.questapp.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId){
        return postService.getAllOrOnePosts(userId);

    }

    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostCreateRequest){
        return postService.createPost(newPostCreateRequest);

    }
    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest postUpdateRequest){
        return postService.updateOnePost(postId,postUpdateRequest);
    }
    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId)
    {
        postService.deleteOnePost(postId);
    }


    @GetMapping("/{postId}")
    public Post getOnePost(@PathVariable Long postId){
        return postService.getOnePostByPostId(postId);

    }

}
