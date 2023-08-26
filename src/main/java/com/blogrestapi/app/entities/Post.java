package com.blogrestapi.app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(
        name = "posts",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)
public class Post {
    @Id()
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long Id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "resume", nullable = false)
    private String resume;
    @Column(name = "content", nullable = false)
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();
}
