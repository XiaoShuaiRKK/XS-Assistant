package com.xs.assistant.article.Controller;

import com.xs.DAO.DO.article.ArticleMongoDO;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.article.Service.ESArticleService;
import com.xs.assistant.article.Service.Remote.ESArticleRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class ArticleSearchController {
    @Autowired
    ESArticleService esArticleService;

    @GetMapping("/getAll")
    public ResponseResult<List<ArticleMongoDO>> searchArticleArray(){
        return esArticleService.getArticlesAll();
    }
}
