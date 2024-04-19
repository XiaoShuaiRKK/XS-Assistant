package com.xs.assistant.article.Controller;

import com.xs.DAO.ResponseResult;
import com.xs.assistant.article.Service.Remote.ArticleLikedRemoteService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/liked")
@Validated
public class ArticleLikedController {
    @Autowired
    ArticleLikedRemoteService likedService;
    @PostMapping("/liked")
    ResponseResult<Integer> likedByAccount(@RequestParam("articleId")String articleId,
                                           @RequestParam("accountId")String accountId){
        return likedService.likedByAccount(articleId, accountId);
    }

    @PostMapping("/unLiked")
    ResponseResult<Integer> unlikedByAccount(@RequestParam("articleId")String articleId,
                                             @RequestParam("accountId")String accountId){
        return likedService.unlikedByAccount(articleId, accountId);
    }
}
