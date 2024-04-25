package com.xs.DAO.VO.article;

import com.xs.DAO.DO.article.Article;
import com.xs.DAO.DO.article.ArticleContext;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVO {
    @NotNull(message = "context cannot be empty")
    ArticleContext context;
    Article article;
}
