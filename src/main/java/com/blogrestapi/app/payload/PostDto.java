package com.blogrestapi.app.payload;

import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
public class PostDto {
    private Long Id;
    private String title;
    private String resume;
    private String content;
}
