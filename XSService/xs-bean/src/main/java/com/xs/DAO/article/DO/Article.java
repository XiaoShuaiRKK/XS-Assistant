package com.xs.DAO.article.DO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {
    Integer id;
    String articleId;
    @NotEmpty(message = "Author ID cannot be empty")
    String authorId;
    String background;
    String image;
    String logo;
}
