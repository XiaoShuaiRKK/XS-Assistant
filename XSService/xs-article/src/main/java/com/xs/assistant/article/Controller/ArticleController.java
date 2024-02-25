package com.xs.assistant.article.Controller;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.article.VO.ArticleVO;
import com.xs.assistant.article.Service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @PostMapping("/addArticle")
    public ResponseResult<Boolean> addArticle(@Valid @RequestBody ArticleVO articleVO){
        return articleService.addArticle(articleVO);
    }

    @PostMapping("/get/byArticleID")
    public ResponseResult<ArticleVO> findArticleByArticleId(@RequestParam("articleId")String articleId){
        return articleService.findArticleByArticleId(articleId);
    }

    @PostMapping("/get/articles/byTitle")
    public ResponseResult<List<ArticleVO>> findArticleByTitle(@RequestParam("title")String title,
                                                              @RequestParam("page")Integer page,
                                                              @RequestParam("size")Integer size){
        return articleService.findArticleByTitle(title,page,size);
    }
}
