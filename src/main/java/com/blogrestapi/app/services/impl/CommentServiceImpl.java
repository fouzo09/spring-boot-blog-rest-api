package com.blogrestapi.app.services.impl;

import com.blogrestapi.app.entities.Comment;
import com.blogrestapi.app.entities.Post;
import com.blogrestapi.app.exceptions.ResourceNotFoundException;
import com.blogrestapi.app.payload.CommentDto;
import com.blogrestapi.app.payload.PostDto;
import com.blogrestapi.app.repositories.CommentRepository;
import com.blogrestapi.app.repositories.PostRepository;
import com.blogrestapi.app.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private ModelMapper modelMapper;
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(ModelMapper modelMapper, PostRepository postRepository, CommentRepository commentRepository){
        this.modelMapper = modelMapper;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }
    @Override
    public CommentDto create(CommentDto commentDto, Long postId) {

        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Commentaire", "Id", postId.toString()));

        Comment newComment = mapToComment(commentDto);

        newComment.setPost(post);

        Comment createdComment = commentRepository.save(newComment);

        return mapToDto(createdComment);
    }

    @Override
    public List<CommentDto> getCommentsOfPost(Long postId) {

        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Commentaire", "Id", postId.toString()));

        List<Comment> commentsOfPost = commentRepository.findByPostId(postId);

        List<CommentDto> commentDtos = commentsOfPost.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());

        return commentDtos;
    }

    private Comment mapToComment(CommentDto commentDto){
        return modelMapper.map(commentDto, Comment.class);
    }

    private CommentDto mapToDto(Comment comment){
        return modelMapper.map(comment, CommentDto.class);
    }
}
