package com.blogrestapi.app.services;

import com.blogrestapi.app.payload.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);
    List<PostDto> listOfPosts();
}
