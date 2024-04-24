package com.xs.assistant.article.Service.Remote;

import com.xs.DAO.DO.article.ArticleMongoDO;
import com.xs.assistant.article.Service.Fallback.ESArticleFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "XS-ARTICLE-SEARCH", path = "/xs_assistant/search",fallbackFactory = ESArticleFallbackFactory.class)
public interface ESArticleRemoteService {
    @PostMapping("/insert/addArticleArray")
    Boolean insertArticleArray(@RequestBody List<ArticleMongoDO> articles);
    @GetMapping("/query/getAll")
    List<ArticleMongoDO> getArticlesAll();
}
