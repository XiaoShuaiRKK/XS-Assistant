package com.xs.assistant.article.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xs.DAO.DO.article.ArticleExamine;
import com.xs.DAO.VO.article.ArticleExamineVO;
import com.xs.DAO.factory.ArticleExamineFactory;
import com.xs.assistant.article.DAO.ArticleDAO;
import com.xs.assistant.article.DAO.ArticleExamineMapper;
import com.xs.assistant.article.service.ArticleExamineService;
import com.xs.assistant.article.service.ArticleStateService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleExamineServiceImpl implements ArticleExamineService {
    final ArticleExamineMapper articleExamineMapper;
    final ArticleDAO articleDAO;
    final ArticleStateService articleStateService;

    public ArticleExamineServiceImpl(ArticleExamineMapper articleExamineMapper, ArticleDAO articleDAO, ArticleStateService articleStateService) {
        this.articleExamineMapper = articleExamineMapper;
        this.articleDAO = articleDAO;
        this.articleStateService = articleStateService;
    }

    @Override
    public Boolean addExamine(String articleId, Integer examineState, String description) {
        return articleExamineMapper.insert(ArticleExamineFactory.createArticleExamine(articleId, examineState, description)) > 0;
    }

    @Override
    public List<ArticleExamineVO> selectExaminesByArticleId(String articleId) {
        LambdaQueryWrapper<ArticleExamine> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleExamine::getArticleId,articleId);
        List<ArticleExamine> articleExamines = articleExamineMapper.selectList(wrapper);
        List<ArticleExamineVO> articleExamineVOS = new ArrayList<>();
        articleExamines.forEach(articleExamine -> {
            ArticleExamineVO item = ArticleExamineFactory.createArticleExamineVO(articleExamine,
                    articleStateService.findById(articleExamine.getExamineState()).getStateName());
            articleExamineVOS.add(item);
        });
        return articleExamineVOS;
    }
}
