package com.flexisaf.spring_crud.controller;

import com.flexisaf.spring_crud.service.CommentService;
import com.flexisaf.spring_crud.view.CommentSummaryView;
import com.flexisaf.spring_crud.view.CommentView;
import com.flexisaf.spring_crud.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // Get all comments
    @GetMapping("/get-comments")
    public ResponseEntity<List<CommentView>> getAllComments() {
        List<CommentView> comments = commentService.findAllComments();
        return ResponseEntity.ok(comments);
    }

    // Get comments by post ID
    @GetMapping("/get-comments-by-post-id")
    public ResponseEntity<List<CommentView>> getCommentsByPostId(@RequestParam("postId") Long postId) {
        List<CommentView> comments = commentService.findCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    // Create a new comment
    @PostMapping("/create-comment")
    public ResponseEntity<CommentSummaryView> createComment(@RequestBody Comment comment) {
        CommentSummaryView savedComment = commentService.saveComment(comment);
        return ResponseEntity.ok(savedComment);
    }
}
