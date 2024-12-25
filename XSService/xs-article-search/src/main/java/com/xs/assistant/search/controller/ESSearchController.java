package com.xs.assistant.search.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.search.service.ESSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/query")
public class ESSearchController {
    final ESSearchService esSearchService;

    public ESSearchController(ESSearchService esSearchService) {
        this.esSearchService = esSearchService;
    }

    /**
     * 从es中查询index为article的所有数据
     * 不推荐使用
     * 建议使用下面具有分页查询功能的接口
     * @return 文章列表
     */
    @GetMapping("/getAll")
    public List<ArticleContext> getArticlesAll(){
        return esSearchService.searchArticlesAll();
    }

    /**
     * 从es中查询index为article的数据
     * @param page 页数
     * @param size 大小
     * @return 文章列表
     */
    @GetMapping("/get")
    public List<ArticleContext> getArticlesByPage(@RequestParam("page")Integer page,
                                                  @RequestParam("size")Integer size){
        return esSearchService.searchArticlesAll(backFrom(page,size),size);
    }

    /**
     * 指定某个字段传入关键字来查询文章
     * @param page 页数
     * @param size 大小
     * @param field 字段
     * @param target 关键字
     * @return 文章列表
     */
    @GetMapping("/get/byField")
    public List<ArticleContext> getArticlesQuery(@RequestParam("page")Integer page,
                                                 @RequestParam("size")Integer size,
                                                 @RequestParam("field")String field,
                                                 @RequestParam("target")String target){
        return esSearchService.searchArticlesQuery(field, target, backFrom(page,size), size);
    }

    /**
     * 根据关键字查询对应的文章
     * @param target 关键字
     * @param page 页数
     * @param size 大小
     * @return 文章列表
     */
    @GetMapping("/get/byAllSearch")
    public List<ArticleContext> getArticleAllQuery(@RequestParam("target")String target,
                                                   @RequestParam("page")Integer page,
                                                   @RequestParam("size")Integer size){
        return esSearchService.searchArticlesAllQuery(target,backFrom(page,size),size);
    }

    /**
     * 根据关键字的匹配程度来将文章进行排序
     * @param field 字段
     * @param target 关键字
     * @param page 页面
     * @param size 大小
     * @return 文章列表
     */
    @GetMapping("/get/byScore")
    public List<ArticleContext> getArticleScoreQuery(@RequestParam("field")String field,
                                                     @RequestParam("target")String target,
                                                     @RequestParam("page")Integer page,
                                                     @RequestParam("size")Integer size){
        return esSearchService.searchArticlesScoreQuery(field,target,backFrom(page,size),size);
    }

    /**
     * 特殊待归位接口
     * 根据关键字来查询，并根据article中的热度值来进行排序
     * @param target 关键字
     * @param page 页数
     * @param size 大小
     * @return 文章列表
     */
    @GetMapping("/get/orderHot")
    public List<ArticleContext> getArticleByTargetOrderHot(@RequestParam("target")String target,
                                                                          @RequestParam("page")Integer page,
                                                                          @RequestParam("size")Integer size){
        log.error("===============Search Article=================");
        if(StringUtils.isNotEmpty(target)){
            return esSearchService.searchArticlesByTargetOrderByHot(backFrom(page,size),size,target);
        }
        return esSearchService.searchArticlesOrderByHot(backFrom(page,size),size);
    }

    @GetMapping("/get/target/authorId")
    public List<ArticleContext> getArticlesByTargetFindAuthorId(@RequestParam("author_id")String authorId,
                                                                @RequestParam("page")Integer page,
                                                                @RequestParam("size")Integer size){
        return esSearchService.searchArticlesByIdNumber(authorId,backFrom(page,size),size);
    }

    private int backFrom(int page,int size){
        return (page - 1) * size;
    }
}
