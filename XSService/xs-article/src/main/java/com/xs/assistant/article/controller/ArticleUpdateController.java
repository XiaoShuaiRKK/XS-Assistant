package com.xs.assistant.article.controller;

import com.xs.DAO.ResponseResult;
import com.xs.assistant.article.service.ArticleUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article/update")
@Slf4j
public class ArticleUpdateController {
    final ArticleUpdateService articleUpdateService;

    public ArticleUpdateController(ArticleUpdateService articleUpdateService) {
        this.articleUpdateService = articleUpdateService;
    }

    @GetMapping("")
    public ResponseResult<Boolean> changeArticleState(@RequestParam("article_id")String articleId,
                                                      @RequestParam("stateId")Integer stateId) {
        return ResponseResult.success(articleUpdateService.changeArticleState(articleId,stateId));
    }
}
