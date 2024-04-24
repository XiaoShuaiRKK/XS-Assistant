package com.xs.DAO.DO.article;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable,Comparable<Article> {
    Integer id;
    String articleId;
    @NotEmpty(message = "Author ID cannot be empty")
    String authorId;
    String background;
    String image;
    String logo;
    Date createTime;
    Integer stateId;

    @Override
    public int compareTo(Article o) {
        long oId = Long.parseLong(o.articleId.substring(0, 4));
        long mId = Long.parseLong(this.articleId.substring(0, 4));
        return mId > oId ? 1 : 0;
    }
}
