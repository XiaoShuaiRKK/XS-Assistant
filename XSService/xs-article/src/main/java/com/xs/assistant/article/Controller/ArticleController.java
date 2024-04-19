package com.xs.assistant.article.Controller;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.VO.article.ArticleVO;
import com.xs.DAO.DO.liked.LikedDO;
import com.xs.assistant.article.Service.ArticleService;
import com.xs.assistant.article.Service.Remote.ArticleLikedRemoteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleLikedRemoteService articleLikedRemoteService;

    @GetMapping("/getArticles")
    public ResponseResult<List<ArticleVO>> getArticles(@Min(value = 1,message = "Please enter the correct value")
                                                           @RequestParam("page") Integer page,
                                                       @Min(value = 1,message = "Please enter the correct value")
                                                       @RequestParam("size") Integer size){
        return articleService.getArticles(page,size);
    }

    @PostMapping("/addArticle")
    public ResponseResult<Boolean> addArticle(@Valid @RequestBody ArticleVO articleVO){
        return articleService.addArticle(articleVO);
    }

    @PostMapping("/get/byArticleID")
    public ResponseResult<ArticleVO> findArticleByArticleId(@RequestParam("articleId")String articleId){
        return articleService.findArticleByArticleId(articleId);
    }

    @PostMapping("/get/articles/bySubTitle")
    public ResponseResult<List<ArticleVO>> findArticleByTitle(@RequestParam("title")String title,
                                                              @RequestParam("page")Integer page,
                                                              @RequestParam("size")Integer size){
        return articleService.findArticleBySubTitle(title,page,size);
    }
    @PostMapping("/get/articles/byTitleOrSubTitle")
    public ResponseResult<List<ArticleVO>> findArticleByTitleOrSubTitle(@RequestParam("title")String title,
                                                              @RequestParam("page")Integer page,
                                                              @RequestParam("size")Integer size){
        return articleService.findArticleByTitleOrSubTilte(title,page,size);
    }

    @GetMapping("/get/top/getTop")
    public ResponseResult<Map<String, Object>> getTopArticle(@RequestParam("count")Long count){
        List<LikedDO> articleLikedTop = articleLikedRemoteService.getTop(count).getData();
        List<String> articleIds = articleLikedTop.stream().map(LikedDO::getArticleId).toList();
        List<ArticleVO> articleVOS = articleService.findArticlesByArticleIds(articleIds).getData();
        return ResponseResult.success(Map.of("liked",articleLikedTop,"articles",articleVOS));
    }
}
