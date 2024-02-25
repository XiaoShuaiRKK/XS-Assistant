package com.xs.DAO.article.VO;

import com.xs.DAO.article.DO.Article;
import com.xs.DAO.article.DO.ArticleMongoDO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVO {
    @NotNull(message = "context cannot be empty")
    ArticleMongoDO context;
    Article article;
}
