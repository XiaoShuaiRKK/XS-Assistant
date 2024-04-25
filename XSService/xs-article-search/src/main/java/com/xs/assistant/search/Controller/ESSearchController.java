package com.xs.assistant.search.Controller;

import com.xs.DAO.DO.article.ArticleContext;
import com.xs.assistant.search.Service.ESSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/query")
public class ESSearchController {
    @Autowired
    ESSearchService esSearchService;

    @GetMapping("/getAll")
    public List<ArticleContext> getArticlesAll(){
        return esSearchService.searchArticlesAll();
    }

    @GetMapping("/get")
    public List<ArticleContext> getArticlesByPage(@RequestParam("page")Integer page,
                                                  @RequestParam("size")Integer size){
        return esSearchService.searchArticlesAll(page,size);
    }

    @GetMapping("/get/byField")
    public List<ArticleContext> getArticlesQuery(@RequestParam("page")Integer page,
                                                 @RequestParam("size")Integer size,
                                                 @RequestParam("field")String field,
                                                 @RequestParam("target")String target){
        return esSearchService.searchArticlesQuery(field, target, page, size);
    }

    @GetMapping("/get/byAllSearch")
    public List<ArticleContext> getArticleAllQuery(@RequestParam("target")String target,
                                                   @RequestParam("page")Integer page,
                                                   @RequestParam("size")Integer size){
        return esSearchService.searchArticlesAllQuery(target,page,size);
    }

    @GetMapping("/get/byScore")
    public List<ArticleContext> getArticleScoreQuery(@RequestParam("field")String field,
                                                     @RequestParam("target")String target,
                                                     @RequestParam("page")Integer page,
                                                     @RequestParam("size")Integer size){
        return esSearchService.searchArticlesScoreQuery(field,target,page,size);
    }
}
