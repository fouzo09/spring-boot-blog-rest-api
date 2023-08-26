package com.blogrestapi.app.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long Id;
    @NotEmpty(message = "Le nom est obligatoire.")
    @Size(min = 3, message = "Le nom doit avoir au moins 3 caracteres")
    private String name;
    @NotEmpty(message = "L'email est obligatoire.")
    @Email(message = "Votre email est incorrect.")
    private String email;
    @NotEmpty(message = "Le commentaire est obligatoire.")
    private String comment;
}
