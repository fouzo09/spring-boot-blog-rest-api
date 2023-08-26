package com.blogrestapi.app.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long Id;
    @NotEmpty(message = "Le titre est obligatoire")
    @Size(min = 6, message = "Le titre doit avoir au moin 6 caracteres")
    private String title;
    @NotEmpty(message = "Le resumé est obligatoire")
    @Size(min = 6, max = 30, message = "Le titre doit avoir au moin 6 caracteres")
    private String resume;
    @NotEmpty(message = "Le contenu est obligatoire")
    @Size(min = 6, message = "Le titre doit avoir au moin 6 caracteres")
    private String content;
}
