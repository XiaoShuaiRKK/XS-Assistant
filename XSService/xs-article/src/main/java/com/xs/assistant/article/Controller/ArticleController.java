package com.xs.assistant.article.Controller;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.article.VO.ArticleVO;
import com.xs.DAO.liked.DO.LikedDO;
import com.xs.assistant.article.Service.ArticleService;
import com.xs.assistant.article.Service.Remote.ArticleLikedRemoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleLikedRemoteService articleLikedRemoteService;

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
        return articleService.findArticleByTitleAndSubTitle(title,page,size);
    }

    @GetMapping("/get/top/getTop")
    public ResponseResult<Map<String, Object>> getTopArticle(@RequestParam("count")Long count){
        List<LikedDO> articleLikedTop = articleLikedRemoteService.getTop(count).getData();
        List<String> articleIds = articleLikedTop.stream().map(LikedDO::getArticleId).toList();
        List<ArticleVO> articleVOS = articleService.findArticlesByAritcleIds(articleIds).getData();
        return ResponseResult.success(Map.of("liked",articleLikedTop,"articles",articleVOS));
    }
}
