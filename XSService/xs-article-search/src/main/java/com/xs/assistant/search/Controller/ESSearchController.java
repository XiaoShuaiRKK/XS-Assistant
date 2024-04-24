package com.xs.assistant.search.Controller;

import com.xs.DAO.DO.article.ArticleMongoDO;
import com.xs.assistant.search.Service.ESSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query")
public class ESSearchController {
    @Autowired
    ESSearchService esSearchService;

    @GetMapping("/getAll")
    public List<ArticleMongoDO> getArticlesAll(){
        return esSearchService.searchArticlesAll();
    }
}
