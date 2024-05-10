package com.xs.DAO.factory;

import com.xs.DAO.DO.article.Article;

public class ArticleFactory {
    public static Article defaultArticle(String authorId,String articleId){
        return new Article(null,articleId,authorId,
                "Background 5","Illustration 1","Logo 2",null,null);
    }
}
