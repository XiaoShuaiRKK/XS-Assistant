package com.xs.assistant.article.controller;

import com.xs.DAO.DO.article.ArticleExamine;
import com.xs.DAO.ResponseResult;
import com.xs.DAO.VO.article.ArticleExamineVO;
import com.xs.assistant.article.service.ArticleExamineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/examine")
@Slf4j
public class ArticleExamineController {
    final ArticleExamineService articleExamineService;

    public ArticleExamineController(ArticleExamineService articleExamineService) {
        this.articleExamineService = articleExamineService;
    }

    @PostMapping("/add")
    public ResponseResult<Boolean> addExamine(@RequestParam("article_id")String articleId,
                                              @RequestParam("examine_state")Integer examineState,
                                              @RequestParam("description")String description) {
        Boolean isSuccess = articleExamineService.addExamine(articleId, examineState, description);
        return Boolean.TRUE.equals(isSuccess) ? ResponseResult.success(true) : ResponseResult.error(false,"提交失败");
    }

    @GetMapping("/get/byArticleId")
    public ResponseResult<List<ArticleExamineVO>> getExaminesByArticleId(@RequestParam("article_id")String articleId) {
        return ResponseResult.success(articleExamineService.selectExaminesByArticleId(articleId));
    }
}
