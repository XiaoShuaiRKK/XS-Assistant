package com.xs.assistant.article.Controller;

import com.xs.DAO.DO.article.ArticleMongoDO;
import com.xs.DAO.ResponseResult;
import com.xs.DAO.VO.article.ArticleVO;
import com.xs.assistant.article.Service.ArticleAddService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insert")
@Validated
public class ArticleInsertController {
    @Autowired
    ArticleAddService addArticle;

    @PostMapping("/addArticle")
    public ResponseResult<Boolean> addArticle(@RequestBody ArticleMongoDO article){
        return addArticle.addArticle(article);
    }
}
