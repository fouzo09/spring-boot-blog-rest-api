package com.blogrestapi.app.services.impl;

import com.blogrestapi.app.entities.Post;
import com.blogrestapi.app.exceptions.ResourceNotFoundException;
import com.blogrestapi.app.payload.PostDto;
import com.blogrestapi.app.repositories.PostRepository;
import com.blogrestapi.app.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        return mapToDTO(createdPost);
    }

    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<Post> posts = postRepository.findAll(pageable).getContent();
        return posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long Id) {
        Post post = postRepository.findById(Id).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", Id.toString()));
        return mapToDTO(post);
    }

    @Override
    public PostDto updatePostById(PostDto postDto, Long Id) {
        Post post = postRepository.findById(Id).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", Id.toString()));
        post.setTitle(postDto.getTitle());
        post.setResume(postDto.getResume());
        post.setContent(postDto.getContent());
        Post updatedPost = postRepository.save(post);
        return mapToDTO(updatedPost);
    }

    @Override
    public void deletePostById(Long Id) {
        Post post = postRepository.findById(Id).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", Id.toString()));
        postRepository.delete(post);
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

    private Post mapToEntityWithId(PostDto postDto, Long Id){
        Post post = new Post();
        post.setId(Id);
        post.setTitle(postDto.getTitle());
        post.setResume(postDto.getResume());
        post.setContent(postDto.getContent());

        return  post;
    }
}
