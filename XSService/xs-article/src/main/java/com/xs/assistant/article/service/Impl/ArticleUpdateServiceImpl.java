package com.xs.assistant.article.service.Impl;

import com.xs.assistant.article.DAO.ArticleDAO;
import com.xs.assistant.article.DAO.ArticleMapper;
import com.xs.assistant.article.service.ArticleUpdateService;
import org.springframework.stereotype.Service;

@Service
public class ArticleUpdateServiceImpl implements ArticleUpdateService {
    final ArticleDAO articleDAO;

    public ArticleUpdateServiceImpl(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Override
    public Boolean changeArticleState(String articleId, int stateId) {
        return articleDAO.changeArticleState(articleId,stateId) > 0;
    }
}
