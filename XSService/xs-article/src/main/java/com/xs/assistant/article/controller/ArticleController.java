package com.xs.assistant.article.controller;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.VO.article.ArticleVO;
import com.xs.DAO.DO.liked.LikedDO;
import com.xs.assistant.article.service.ArticleService;
import com.xs.assistant.article.service.remote.ArticleLikedRemoteService;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 不推荐使用此接口类
 * 此接口类待废弃或扩展
 */
@Validated
@RestController
@RequestMapping
public class ArticleController {

    final ArticleService articleService;
    final ArticleLikedRemoteService articleLikedRemoteService;

    public ArticleController(ArticleService articleService, ArticleLikedRemoteService articleLikedRemoteService) {
        this.articleService = articleService;
        this.articleLikedRemoteService = articleLikedRemoteService;
    }

    /**
     * 获取文章列表
     * @param page 页数
     * @param size 大小
     * @return 文章列表
     */
    @GetMapping("/getArticles")
    public ResponseResult<List<ArticleVO>> getArticles(@Min(value = 1,message = "Please enter the correct value")
                                                           @RequestParam("page") Integer page,
                                                       @Min(value = 1,message = "Please enter the correct value")
                                                       @RequestParam("size") Integer size){
        return articleService.getArticles(page,size);
    }

    /**
     * 根据文章id获取文章
     * @param articleId 文章id
     * @return 文章
     */
    @PostMapping("/get/byArticleID")
    public ResponseResult<ArticleVO> findArticleByArticleId(@RequestParam("articleId")String articleId){
        return articleService.findArticleByArticleId(articleId);
    }

    /**
     * 根据关键字来查找对应副标题的文章
     * @param title 关键字
     * @param page 页数
     * @param size 大小
     * @return 文章列表
     */
    @PostMapping("/get/articles/bySubTitle")
    public ResponseResult<List<ArticleVO>> findArticleByTitle(@RequestParam("title")String title,
                                                              @RequestParam("page")Integer page,
                                                              @RequestParam("size")Integer size){
        return articleService.findArticleBySubTitle(title,page,size);
    }

    /**
     * 根据关键字来查找对应的标题和副标题的文章
     * @param title 关键字
     * @param page 页数
     * @param size 大小
     * @return 文章列表
     */
    @PostMapping("/get/articles/byTitleOrSubTitle")
    public ResponseResult<List<ArticleVO>> findArticleByTitleOrSubTitle(@RequestParam("title")String title,
                                                              @RequestParam("page")Integer page,
                                                              @RequestParam("size")Integer size){
        return articleService.findArticleByTitleOrSubTitle(title,page,size);
    }

    /**
     * 获取所有文章 按照点赞数量来进行排序
     * @param count 数量
     * @return 文章列表
     */
    @GetMapping("/get/top/getTop")
    public ResponseResult<Map<String, Object>> getTopArticle(@RequestParam("count")Long count){
        List<LikedDO> articleLikedTop = articleLikedRemoteService.getTop(count).getData();
        List<String> articleIds = articleLikedTop.stream().map(LikedDO::getArticleId).toList();
        List<ArticleVO> articleVOS = articleService.findArticlesByArticleIds(articleIds).getData();
        return ResponseResult.success(Map.of("liked",articleLikedTop,"articles",articleVOS));
    }
}
