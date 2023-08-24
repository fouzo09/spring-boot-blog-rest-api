package com.blogrestapi.app.services;

import com.blogrestapi.app.payload.PostDto;

public interface PostService {

    PostDto createPost(PostDto postDto);
}
