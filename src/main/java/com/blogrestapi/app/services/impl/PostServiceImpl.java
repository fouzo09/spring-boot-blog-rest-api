package com.blogrestapi.app.services.impl;

import com.blogrestapi.app.entities.Post;
import com.blogrestapi.app.payload.PostDto;
import com.blogrestapi.app.repositories.PostRepository;
import com.blogrestapi.app.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }
    @Override
    public PostDto createPost(PostDto postDto) {

        Post newPost = mapToEntity(postDto);
        Post createdPost = postRepository.save(newPost);
        PostDto postDtoResponse = mapToDTO(createdPost);

        return postDtoResponse;
    }

    @Override
    public List<PostDto> listOfPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
    }

    private PostDto mapToDTO(Post post){
        PostDto postDto = new PostDto();

        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setResume(post.getResume());
        postDto.setContent(post.getContent());

        return postDto;
    }

    private Post mapToEntity(PostDto postDto){
        Post post = new Post();

        post.setTitle(postDto.getTitle());
        post.setResume(postDto.getResume());
        post.setContent(postDto.getContent());

        return  post;
    }
}
