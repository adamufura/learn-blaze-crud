package com.flexisaf.spring_crud.controller;

import com.flexisaf.spring_crud.service.PostService;
import com.flexisaf.spring_crud.view.PostView;
import com.flexisaf.spring_crud.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // Get all posts
    @GetMapping("/get-posts")
    public ResponseEntity<List<PostView>> getAllPosts() {
        List<PostView> posts = postService.findAllPosts();
        return ResponseEntity.ok(posts);
    }

    // Get posts by user ID
    @GetMapping("/get-posts-by-user-id")
    public ResponseEntity<List<PostView>> getPostsByUserId(@RequestParam("userId") Long userId) {
        List<PostView> posts = postService.findPostsByUserId(userId);
        return ResponseEntity.ok(posts);
    }

    // Create a new post
    @PostMapping("/create")
    public ResponseEntity<PostView> createPost(@RequestBody Post post, @RequestParam Long userId) {
        // Save the post and return the corresponding PostView
        PostView savedPostView = postService.savePost(post, userId);
        return ResponseEntity.ok(savedPostView);
    }
}
