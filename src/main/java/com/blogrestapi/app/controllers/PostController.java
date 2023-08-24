package com.blogrestapi.app.controllers;

import com.blogrestapi.app.payload.PostDto;
import com.blogrestapi.app.payload.PostResponse;
import com.blogrestapi.app.repositories.PostRepository;
import com.blogrestapi.app.services.PostService;
import com.blogrestapi.app.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/posts")
public class PostController {
    private PostService postService;
    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> create(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponse list(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ){
        return postService.getAllPosts(pageNo, pageSize);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable(name = "id") Long Id){
        return ResponseEntity.ok(postService.getPostById(Id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") Long Id){
        return ResponseEntity.ok(postService.updatePostById(postDto, Id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") Long Id){
        postService.deletePostById(Id);
        return new ResponseEntity<>("Le Post est supprimé avec succès", HttpStatus.OK);
    }
}
