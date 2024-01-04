package com.onuralan.questapp.services;

import com.onuralan.questapp.entities.Post;
import com.onuralan.questapp.entities.User;
import com.onuralan.questapp.repos.PostRepository;
import com.onuralan.questapp.requests.PostCreateRequest;
import com.onuralan.questapp.requests.PostUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.userService = userService;
        this.postRepository = postRepository;
    }

    public List<Post> getAllOrOnePosts(Optional<Long> userId) {
        if (userId.isPresent())
            return postRepository.findByUserId(userId.get());
        else
            return postRepository.findAll();
    }

    public Post getOnePostByPostId(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createPost(PostCreateRequest newPostCreateRequest) {
        User user = userService.getOneUser(newPostCreateRequest.getUserId());
        if (user == null)
            return null;
        Post toSave = new Post();
        toSave.setId(newPostCreateRequest.getId());
        toSave.setText(newPostCreateRequest.getText());
        toSave.setTitle(newPostCreateRequest.getTitle());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }

    public Post updateOnePost(Long postId, PostUpdateRequest postUpdateRequest) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            Post toUpdate = post.get();
            toUpdate.setText(postUpdateRequest.getText());
            toUpdate.setTitle(postUpdateRequest.getTitle());
            return postRepository.save(toUpdate);
        }

        return null;



    }

    public void deleteOnePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
