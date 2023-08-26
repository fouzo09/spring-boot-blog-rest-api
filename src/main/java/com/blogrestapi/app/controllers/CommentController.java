package com.blogrestapi.app.controllers;

import com.blogrestapi.app.payload.CommentDto;
import com.blogrestapi.app.repositories.CommentRepository;
import com.blogrestapi.app.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/posts/{postId}")
public class CommentController {

    private CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }
    @PostMapping("/comments")
    public ResponseEntity<CommentDto> create(@Valid @RequestBody CommentDto commentDto,
                                             @PathVariable("postId") Long postId){
        CommentDto createdCommentDto = commentService.create(commentDto, postId);
        return new ResponseEntity<>(createdCommentDto, HttpStatus.CREATED);
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> commentOfPost(@PathVariable("postId") Long postId){
        List<CommentDto> commentDtos = commentService.getCommentsOfPost(postId);
        return ResponseEntity.ok(commentDtos);
    }
}
