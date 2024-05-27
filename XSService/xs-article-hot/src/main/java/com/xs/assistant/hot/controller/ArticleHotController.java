package com.xs.assistant.hot.controller;

import com.xs.assistant.hot.service.ArticleHotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 待扩展
 */
@RestController
@RequestMapping("/operate")
public class ArticleHotController {
    final ArticleHotService articleHotService;

    public ArticleHotController(ArticleHotService articleHotService) {
        this.articleHotService = articleHotService;
    }

    /**
     * 评论数加一
     * @param articleId 文章id
     * @return 是否成功
     */
    @GetMapping("/comment")
    public Boolean addComment(@RequestParam("articleId") String articleId){
        return articleHotService.addComment(articleId);
    }

    /**
     * 收藏数加一
     * @param articleId 文章id
     * @return 是否成功
     */
    @GetMapping("/star")
    public Boolean addStar(@RequestParam("articleId") String articleId){
        return articleHotService.addStar(articleId);
    }

    /**
     * 点赞数加一
     * @param articleId 文章id
     * @return 是否成功
     */
    @GetMapping("/liked")
    public Boolean addLiked(@RequestParam("articleId")String articleId){
        return articleHotService.addLiked(articleId);
    }

    /**
     * 评论数减一
     * @param articleId 文章id
     * @return 是否成功
     */
    @GetMapping("/cancel/comment")
    public Boolean cancelComment(@RequestParam("articleId")String articleId){
        return articleHotService.cancelComment(articleId);
    }

    /**
     * 收藏数减一
     * @param articleId 文章id
     * @return 是否成功
     */
    @GetMapping("/cancel/star")
    public Boolean cancelStar(@RequestParam("articleId")String articleId){
        return articleHotService.cancelStar(articleId);
    }

    /**
     * 点赞数减一
     * @param articleId 文章id
     * @return 是否成功
     */
    @GetMapping("/cancel/liked")
    public Boolean cancelLiked(@RequestParam("articleId")String articleId){
        return articleHotService.cancelLiked(articleId);
    }
}
