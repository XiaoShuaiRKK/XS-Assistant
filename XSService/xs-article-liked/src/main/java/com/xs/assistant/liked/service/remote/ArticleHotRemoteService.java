package com.xs.assistant.liked.service.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "XS-ARTICLE-HOT",path = "/xs_assistant/article/hot/operate")
public interface ArticleHotRemoteService {
    @GetMapping("/comment")
    Boolean addComment(@RequestParam("articleId") String articleId);

    @GetMapping("/star")
    Boolean addStar(@RequestParam("articleId") String articleId);

    @GetMapping("/liked")
    Boolean addLiked(@RequestParam("articleId")String articleId);

    @GetMapping("/cancel/comment")
    Boolean cancelComment(@RequestParam("articleId")String articleId);

    @GetMapping("/cancel/star")
    Boolean cancelStar(@RequestParam("articleId")String articleId);

    @GetMapping("/cancel/liked")
    Boolean cancelLiked(@RequestParam("articleId")String articleId);
}
