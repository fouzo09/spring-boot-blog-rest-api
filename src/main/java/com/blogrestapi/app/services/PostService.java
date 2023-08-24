package com.blogrestapi.app.services;

import com.blogrestapi.app.payload.PostDto;
import com.blogrestapi.app.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize);
    PostDto getPostById(Long Id);
    PostDto updatePostById(PostDto postDto, Long Id);
    void deletePostById(Long Id);
}
