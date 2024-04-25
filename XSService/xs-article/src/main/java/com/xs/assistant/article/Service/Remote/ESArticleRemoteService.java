package com.xs.assistant.article.Service.Remote;

import com.xs.DAO.DO.article.ArticleContext;
import com.xs.assistant.article.Service.Fallback.ESArticleFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "XS-ARTICLE-SEARCH", path = "/xs_assistant/search",fallbackFactory = ESArticleFallbackFactory.class)
public interface ESArticleRemoteService {
    @PostMapping("/insert/addArticleArray")
    Boolean insertArticleArray(@RequestBody List<ArticleContext> articles);
    @GetMapping("/query/getAll")
    List<ArticleContext> getArticlesAll();

    @GetMapping("/query/get")
    List<ArticleContext> getArticlesByPage(@RequestParam("page")Integer page,
                                           @RequestParam("size")Integer size);

    @GetMapping("/query/get/byField")
    List<ArticleContext> getArticlesQuery(@RequestParam("page")Integer page,
                                          @RequestParam("size")Integer size,
                                          @RequestParam("field")String field,
                                          @RequestParam("target")String target);

    @GetMapping("/query/get/byAllSearch")
    List<ArticleContext> getArticleAllQuery(@RequestParam("target")String target,
                                            @RequestParam("page")Integer page,
                                            @RequestParam("size")Integer size);

    @GetMapping("/query/get/byScore")
    List<ArticleContext> getArticleScoreQuery(@RequestParam("field")String field,
                                              @RequestParam("target")String target,
                                              @RequestParam("page")Integer page,
                                              @RequestParam("size")Integer size);
}
