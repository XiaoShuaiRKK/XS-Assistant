package com.xs.assistant.hot.controller;

import com.xs.assistant.hot.service.SearchArticleHot;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/search")
public class ArticleHotSearchController {

    final SearchArticleHot searchArticleHot;

    public ArticleHotSearchController(SearchArticleHot searchArticleHot) {
        this.searchArticleHot = searchArticleHot;
    }

    @GetMapping("/all")
    public Set<Object> getAllHotTop(){
        return searchArticleHot.getHotTop();
    }

    @GetMapping("/top")
    public Set<Object> getHotTop(@RequestParam("start")Long start,@RequestParam("end")Long end){
        return searchArticleHot.getHotTop(start,end);
    }
}
