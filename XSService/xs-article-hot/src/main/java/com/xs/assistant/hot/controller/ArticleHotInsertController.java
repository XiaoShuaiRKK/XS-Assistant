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

    /**
     * 插入空的文章热度
     * @param articleId 文章id
     * @return 是否成功
     */
    @PostMapping("/default")
    public Boolean insertDefaultHot(@RequestParam("articleId")String articleId){
        return articleHotInsertService.insertDefaultHot(articleId);
    }

    /**
     * 插入对应数据的文章热度
     * @param articleId 文章id
     * @param comment 评论数
     * @param star 收藏数
     * @param liked 点赞数
     * @return 是否成功
     */
    @PostMapping("/hasValue")
    public Boolean insertHasValueHot(@RequestParam("articleId")String articleId,
                                     @RequestParam("comment")Long comment,
                                     @RequestParam("star")Long star,
                                     @RequestParam("liked")Long liked){
        return articleHotInsertService.insertHasValueHot(articleId,comment,star,liked);
    }

    /**
     * 插入对应数据的文章热度
     * @param articleHot 文章热度
     * @return 是否成功
     */
    @PostMapping("/hasValueByBean")
    public Boolean insertHasValueHot(@RequestBody ArticleHot articleHot){
        return articleHotInsertService.insertHasValueHot(articleHot);
    }
}
