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

    /**
     * 插入新的文章热度
     * 默认数据为初始化
     * @param articleId 文章id
     * @return 是否成功
     */
    @Override
    public Boolean insertDefaultHot(String articleId) {
        int result = articleHotInsertMapper.insert(new ArticleHot(null,articleId,
                0L,0L,0L,new Date(System.currentTimeMillis())));
        return result > 0;
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
        int result = articleHotInsertMapper.insert(ArticleHot.builder().articleId(articleId)
                .commentNum(comment).starNum(star).likedNum(liked)
                .createTime(new Date(System.currentTimeMillis())).build());
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
}
