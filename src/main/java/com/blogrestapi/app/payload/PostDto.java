package com.blogrestapi.app.payload;

import lombok.Data;

@Data
public class PostDto {
    private Long Id;
    private String title;
    private String resume;
    private String content;
}
