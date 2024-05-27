package com.xs.assistant.article.controller;

import com.xs.DAO.ResponseResult;
import com.xs.assistant.article.service.remote.ArticleLikedRemoteService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/liked")
@Validated
public class ArticleLikedController {
    final ArticleLikedRemoteService likedService;

    public ArticleLikedController(ArticleLikedRemoteService likedService) {
        this.likedService = likedService;
    }

    /**
     * 点赞
     * @param articleId 文章id
     * @param accountId 用户id
     * @return 是否成功
     */
    @PostMapping("/liked")
    ResponseResult<Integer> likedByAccount(@RequestParam("articleId")String articleId,
                                           @RequestParam("accountId")String accountId){
        return likedService.likedByAccount(articleId, accountId);
    }

    /**
     * 取消点赞
     * @param articleId 文章id
     * @param accountId 用户id
     * @return 是否成功
     */
    @PostMapping("/unLiked")
    ResponseResult<Integer> unlikedByAccount(@RequestParam("articleId")String articleId,
                                             @RequestParam("accountId")String accountId){
        return likedService.unlikedByAccount(articleId, accountId);
    }
}
