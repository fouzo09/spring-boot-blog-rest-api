package com.blogrestapi.app.services;

import com.blogrestapi.app.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto create(CommentDto commentDto, Long produitId);
    List<CommentDto> getCommentsOfPost(Long postId);
}
