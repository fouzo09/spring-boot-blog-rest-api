package com.blogrestapi.app.services.impl;

import com.blogrestapi.app.entities.Post;
import com.blogrestapi.app.payload.PostDto;
import com.blogrestapi.app.repositories.PostRepository;
import com.blogrestapi.app.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }
    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = new Post();
        PostDto postDtoResponse = new PostDto();

        post.setTitle(postDto.getTitle());
        post.setResume(postDto.getResume());
        post.setContent(postDto.getContent());

        Post createdPost = postRepository.save(post);

        postDtoResponse.setId(createdPost.getId());
        postDtoResponse.setTitle(createdPost.getTitle());
        postDtoResponse.setResume(createdPost.getResume());
        postDtoResponse.setContent(createdPost.getContent());

        return postDtoResponse;
    }
}
