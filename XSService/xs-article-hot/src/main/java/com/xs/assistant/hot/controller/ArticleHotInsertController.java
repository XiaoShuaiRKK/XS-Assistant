package com.xs.assistant.hot.controller;

import com.xs.DAO.DO.article.ArticleHot;
import com.xs.assistant.hot.service.ArticleHotInsertService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insert")
public class ArticleHotInsertController {
    final ArticleHotInsertService articleHotInsertService;

    public ArticleHotInsertController(ArticleHotInsertService articleHotInsertService) {
        this.articleHotInsertService = articleHotInsertService;
    }

    @PostMapping("/default")
    public Boolean insertDefaultHot(@RequestParam("articleId")String articleId){
        return articleHotInsertService.insertDefaultHot(articleId);
    }

    @PostMapping("/hasValue")
    public Boolean insertHasValueHot(@RequestParam("articleId")String articleId,
                                     @RequestParam("comment")Long comment,
                                     @RequestParam("star")Long star,
                                     @RequestParam("liked")Long liked){
        return articleHotInsertService.insertHasValueHot(articleId,comment,star,liked);
    }

    @PostMapping("/hasValueByBean")
    public Boolean insertHasValueHot(@RequestBody ArticleHot articleHot){
        return articleHotInsertService.insertHasValueHot(articleHot);
    }
}
