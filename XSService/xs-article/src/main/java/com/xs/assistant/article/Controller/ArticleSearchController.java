package com.xs.assistant.article.Controller;

import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.article.Service.ESArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class ArticleSearchController {
    @Autowired
    ESArticleService esArticleService;

    @GetMapping("/getAll")
    public ResponseResult<List<ArticleContext>> searchArticleArray(){
        return esArticleService.getArticlesAll();
    }

    @GetMapping("/get/byAllQuery")
    public ResponseResult<List<ArticleContext>> searchArticleByAllFieldQuery(@RequestParam("target")String target,
                                                                             @RequestParam("page")Integer page,
                                                                             @RequestParam("size")Integer size){
        return esArticleService.getArticlesByAllField(target,page,size);
    }

    @GetMapping("/get/byScore")
    public ResponseResult<List<ArticleContext>> searchArticleByScoreQuery(@RequestParam("field")String field,
                                                                          @RequestParam("target")String target,
                                                                          @RequestParam("page")Integer page,
                                                                          @RequestParam("size")Integer size){
        return esArticleService.getArticlesByScore(field,target,page,size);
    }
}
