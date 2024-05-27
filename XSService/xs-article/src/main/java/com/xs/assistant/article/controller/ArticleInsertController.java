package com.xs.assistant.article.controller;

import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.article.service.ArticleAddService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insert")
@Validated
public class ArticleInsertController {
    final ArticleAddService addArticle;

    public ArticleInsertController(ArticleAddService addArticle) {
        this.addArticle = addArticle;
    }

    /**
     * 添加文章
     * @param article 文章
     * @return 是否成功
     */
    @PostMapping("/addArticle")
    public ResponseResult<Boolean> addArticle(@RequestBody ArticleContext article){
        return addArticle.addArticle(article);
    }
}
