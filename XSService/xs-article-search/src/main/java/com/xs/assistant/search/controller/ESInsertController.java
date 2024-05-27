package com.xs.assistant.search.controller;

import com.xs.DAO.DO.article.ArticleContext;
import com.xs.assistant.search.service.ESInsertService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insert")
public class ESInsertController {
    final ESInsertService esInsertService;

    public ESInsertController(ESInsertService esInsertService) {
        this.esInsertService = esInsertService;
    }

    /**
     * 添加文章
     * @param articles 文章
     * @return 是否成功
     */
    @PostMapping("/addArticleArray")
    public Boolean insertArticleArray(@RequestBody List<ArticleContext> articles){
        return esInsertService.insert(articles);
    }
}
