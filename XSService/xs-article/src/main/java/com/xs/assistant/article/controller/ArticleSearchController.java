package com.xs.assistant.article.controller;

import com.xs.DAO.DO.article.ArticleState;
import com.xs.DAO.ResponseResult;
import com.xs.DAO.VO.article.ArticleContextVO;
import com.xs.assistant.article.service.ArticleStateService;
import com.xs.assistant.article.service.ESArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class ArticleSearchController {
    final ESArticleService esArticleService;
    final ArticleStateService articleStateService;

    public ArticleSearchController(ESArticleService esArticleService, ArticleStateService articleStateService) {
        this.esArticleService = esArticleService;
        this.articleStateService = articleStateService;
    }

    /**
     * 查询所有文章
     * @param page 页数
     * @param size 大小
     * @return 文章列表
     */
    @GetMapping("/get/page")
    public ResponseResult<List<ArticleContextVO>> searchArticleArray(@RequestParam("page")Integer page,
                                                                     @RequestParam("size")Integer size){
        return esArticleService.getArticlesByPage(page,size);
    }

    /**
     * 根据关键字查找文章
     * @param target 关键字
     * @param page 页数
     * @param size 大小
     * @return 文章列表
     */
    @GetMapping("/get/byAllQuery")
    public ResponseResult<List<ArticleContextVO>> searchArticleByAllFieldQuery(@RequestParam("target")String target,
                                                                             @RequestParam("page")Integer page,
                                                                             @RequestParam("size")Integer size){
        return esArticleService.getArticlesByAllField(target,page,size);
    }

    /**
     * 根据关键字的匹配程度来查找文章
     * @param field 字段
     * @param target 关键字
     * @param page 页数
     * @param size 大小
     * @return 文章列表
     */
    @GetMapping("/get/byScore")
    public ResponseResult<List<ArticleContextVO>> searchArticleByScoreQuery(@RequestParam("field")String field,
                                                                            @RequestParam("target")String target,
                                                                            @RequestParam("page")Integer page,
                                                                            @RequestParam("size")Integer size){
        return esArticleService.getArticlesByScore(field,target,page,size);
    }

    @GetMapping("/get/by/idNumber")
    public ResponseResult<List<ArticleContextVO>> searchArticleByTargetFindIdNumber(@RequestParam("id_number")String idNumber,
                                                                                    @RequestParam("page")Integer page,
                                                                                    @RequestParam("size")Integer size){
        return ResponseResult.success(esArticleService.getArticlesByTargetFindIdNumber(idNumber, page, size));
    }

    @GetMapping("/get/orderHot")
    public ResponseResult<List<ArticleContextVO>> searchArticleByTargetOrderHot(@RequestParam("target")String target,
                                                                                @RequestParam("page")Integer page,
                                                                                @RequestParam("size")Integer size){
        return ResponseResult.success(esArticleService.getArticleByTargetOrderHot(target, page, size));
    }

    @GetMapping("/get/allState")
    public ResponseResult<List<ArticleState>> getAllState(){
        return ResponseResult.success(articleStateService.findAll());
    }
}
