package com.xs.assistant.search.Controller;

import com.xs.DAO.DO.article.ArticleMongoDO;
import com.xs.assistant.search.Service.ESInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insert")
public class ESInsertController {
    @Autowired
    ESInsertService esInsertService;

    @PostMapping("/addArticleArray")
    public Boolean insertArticleArray(@RequestBody List<ArticleMongoDO> articles){
        return esInsertService.insert(articles);
    }
}
