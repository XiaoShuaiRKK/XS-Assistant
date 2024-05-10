package com.xs.assistant.hot.controller;

import com.xs.assistant.hot.service.ArticleHotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operate")
public class ArticleHotController {
    final ArticleHotService articleHotService;

    public ArticleHotController(ArticleHotService articleHotService) {
        this.articleHotService = articleHotService;
    }

    @GetMapping("/comment")
    public Boolean addComment(@RequestParam("articleId") String articleId){
        return articleHotService.addComment(articleId);
    }

    @GetMapping("/star")
    public Boolean addStar(@RequestParam("articleId") String articleId){
        return articleHotService.addStar(articleId);
    }

    @GetMapping("/liked")
    public Boolean addLiked(@RequestParam("articleId")String articleId){
        return articleHotService.addLiked(articleId);
    }

    @GetMapping("/cancel/comment")
    public Boolean cancelComment(@RequestParam("articleId")String articleId){
        return articleHotService.cancelComment(articleId);
    }

    @GetMapping("/cancel/star")
    public Boolean cancelStar(@RequestParam("articleId")String articleId){
        return articleHotService.cancelStar(articleId);
    }

    @GetMapping("/cancel/liked")
    public Boolean cancelLiked(@RequestParam("articleId")String articleId){
        return articleHotService.cancelLiked(articleId);
    }
}
