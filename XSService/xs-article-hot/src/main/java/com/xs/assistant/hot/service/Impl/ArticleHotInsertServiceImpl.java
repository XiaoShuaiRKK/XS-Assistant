package com.xs.assistant.hot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xs.DAO.DO.article.ArticleHot;
import com.xs.DAO.factory.ArticleHotFactory;
import com.xs.assistant.hot.DAO.ArticleHotInsertMapper;
import com.xs.assistant.hot.DAO.ArticleHotMapper;
import com.xs.assistant.hot.service.ArticleHotInsertService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleHotInsertServiceImpl implements ArticleHotInsertService {

    final ArticleHotInsertMapper articleHotInsertMapper;
    final ArticleHotMapper articleHotMapper;

    public ArticleHotInsertServiceImpl(ArticleHotInsertMapper articleHotInsertMapper, ArticleHotMapper articleHotMapper) {
        this.articleHotInsertMapper = articleHotInsertMapper;
        this.articleHotMapper = articleHotMapper;
    }


    /**
     * 插入新的文章热度
     * 默认数据为初始化
     * @param articleId 文章id
     * @return 是否成功
     */
    @Override
    public Boolean insertDefaultHot(String articleId) {
        int result = articleHotInsertMapper.insert(ArticleHotFactory.getDefaultArticleHot(articleId));
        return result > 0;
    }

    @Override
    public Boolean batchInsertDefaultHot(List<String> articleIds) {
        List<ArticleHot> articleHots = new ArrayList<>();
        articleIds.forEach(id -> articleHots.add(ArticleHotFactory.getDefaultArticleHot(id)));
        return articleHotMapper.batchInsertHot(articleHots) > 0;
    }

    /**
     * 插入文章热度，带数据
     * @param articleId 文章id
     * @param comment 评论数
     * @param star 收藏数
     * @param liked 点赞数
     * @return 是否成功
     */
    @Override
    public Boolean insertHasValueHot(String articleId, Long comment, Long star, Long liked) {
        Date now = new Date(System.currentTimeMillis());
        int result = articleHotInsertMapper.insert(ArticleHot.builder().articleId(articleId)
                .commentNum(comment).starNum(star).likedNum(liked)
                .createTime(now)
                .updateTime(now).build());
        return result > 0;
    }

    /**
     * 插入文章热度，带数据
     * @param articleHot 文章热度
     * @return 是否成功
     */
    @Override
    public Boolean insertHasValueHot(ArticleHot articleHot) {
        int result = articleHotInsertMapper.insert(articleHot);
        return result > 0;
    }

    @Override
    public Integer deleteHot(String articleId) {
        LambdaQueryWrapper<ArticleHot> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ArticleHot::getArticleId, articleId);
        return articleHotInsertMapper.delete(wrapper);
    }
}
