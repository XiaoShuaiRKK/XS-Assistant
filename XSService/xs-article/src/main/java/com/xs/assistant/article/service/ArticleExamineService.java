package com.xs.assistant.article.service;

import com.xs.DAO.DO.article.ArticleExamine;
import com.xs.DAO.VO.article.ArticleExamineVO;

import java.util.List;

public interface ArticleExamineService {
    Boolean addExamine(String articleId,Integer examineState,String description);
    List<ArticleExamineVO> selectExaminesByArticleId(String articleId);
}
