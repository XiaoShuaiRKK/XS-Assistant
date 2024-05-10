package com.xs.assistant.hot.service.Impl;

import com.xs.DAO.DO.article.ArticleHot;
import com.xs.assistant.hot.DAO.ArticleHotInsertMapper;
import com.xs.assistant.hot.service.ArticleHotInsertService;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class ArticleHotInsertServiceImpl implements ArticleHotInsertService {

    final ArticleHotInsertMapper articleHotInsertMapper;

    public ArticleHotInsertServiceImpl(ArticleHotInsertMapper articleHotInsertMapper) {
        this.articleHotInsertMapper = articleHotInsertMapper;
    }

    @Override
    public Boolean insertDefaultHot(String articleId) {
        int result = articleHotInsertMapper.insert(new ArticleHot(null,articleId,
                0L,0L,0L,new Date(System.currentTimeMillis())));
        return result > 0;
    }

    @Override
    public Boolean insertHasValueHot(String articleId, Long comment, Long star, Long liked) {
        int result = articleHotInsertMapper.insert(ArticleHot.builder().articleId(articleId)
                .commentNum(comment).starNum(star).likedNum(liked)
                .createTime(new Date(System.currentTimeMillis())).build());
        return result > 0;
    }

    @Override
    public Boolean insertHasValueHot(ArticleHot articleHot) {
        int result = articleHotInsertMapper.insert(articleHot);
        return result > 0;
    }
}
