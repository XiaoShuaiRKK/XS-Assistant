package com.xs.assistant.article.Controller;

import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.ResponseResult;
import com.xs.DAO.VO.article.ArticleContextVO;
import com.xs.assistant.article.Service.ESArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class ArticleSearchController {
    @Autowired
    ESArticleService esArticleService;

    @GetMapping("/get/page")
    public ResponseResult<List<ArticleContextVO>> searchArticleArray(@RequestParam("page")Integer page,
                                                                     @RequestParam("size")Integer size){
        return esArticleService.getArticlesByPage(page,size);
    }

    @GetMapping("/get/byAllQuery")
    public ResponseResult<List<ArticleContextVO>> searchArticleByAllFieldQuery(@RequestParam("target")String target,
                                                                             @RequestParam("page")Integer page,
                                                                             @RequestParam("size")Integer size){
        return esArticleService.getArticlesByAllField(target,page,size);
    }

    @GetMapping("/get/byScore")
    public ResponseResult<List<ArticleContextVO>> searchArticleByScoreQuery(@RequestParam("field")String field,
                                                                            @RequestParam("target")String target,
                                                                            @RequestParam("page")Integer page,
                                                                            @RequestParam("size")Integer size){
        return esArticleService.getArticlesByScore(field,target,page,size);
    }
}
