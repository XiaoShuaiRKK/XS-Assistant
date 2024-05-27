package com.xs.assistant.article.service.remote;

import com.xs.DAO.DO.article.ArticleHot;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "XS-ARTICLE-HOT",path = "/xs_assistant/article/hot/insert")
public interface ArticleHotInsertRemoteService {
    @PostMapping("/default")
    Boolean insertDefaultHot(@RequestParam("articleId")String articleId);

    @PostMapping("/hasValue")
    Boolean insertHasValueHot(@RequestParam("articleId")String articleId,
                                     @RequestParam("comment")Long comment,
                                     @RequestParam("star")Long star,
                                     @RequestParam("liked")Long liked);

    @PostMapping("/hasValueByBean")
    Boolean insertHasValueHot(@RequestBody ArticleHot articleHot);
}
