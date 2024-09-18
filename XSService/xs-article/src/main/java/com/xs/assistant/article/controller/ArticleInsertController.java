package com.xs.assistant.article.controller;

import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.article.service.ArticleAddService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
        try {
            return addArticle.addArticle(article).get() ? ResponseResult.success(true,"success")
                    : ResponseResult.error(false,"error");
        } catch (Exception e) {
            return ResponseResult.error(false,e.getMessage());
        }
    }

    @PostMapping("/batch/add/articles")
    public ResponseResult<Boolean> batchAddArticle(@RequestBody List<ArticleContext> articles) throws ExecutionException, InterruptedException {
        List<Future<Boolean>> futures = new ArrayList<>();
        articles.forEach(articleContext -> futures.add(addArticle.addArticle(articleContext)));
        for (Future<Boolean> future : futures) {
            if (Boolean.FALSE.equals(future.get())) {
                return ResponseResult.error(false,"error");
            }
        }
        return ResponseResult.success(true,"success");
    }
}
