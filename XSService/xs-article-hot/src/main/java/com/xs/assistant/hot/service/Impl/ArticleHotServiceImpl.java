package com.xs.assistant.hot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xs.DAO.DO.article.ArticleHot;
import com.xs.DAO.factory.ArticleHotFactory;
import com.xs.DAO.option.OperatorEnum;
import com.xs.DAO.option.OperatorFactory;
import com.xs.assistant.hot.DAO.ArticleHotKey;
import com.xs.assistant.hot.DAO.ArticleHotMapper;
import com.xs.assistant.hot.service.ArticleHotService;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleHotServiceImpl implements ArticleHotService {
    final ArticleHotMapper articleHotMapper;
    final RabbitTemplate rabbitTemplate;

    public ArticleHotServiceImpl(ArticleHotMapper articleHotMapper, RabbitTemplate rabbitTemplate) {
        this.articleHotMapper = articleHotMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 添加评论数 +1
     * @param articleId 文章id
     * @return true 成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Retry(name = "article-hot-info-api")
    public Boolean addComment(String articleId) {
        Long oldValue = articleHotMapper.selectCommentNumByArticleId(articleId);
        return articleHotOperation(oldValue,articleId,OperatorEnum.INCREMENT, ArticleHotFactory.ArticleHotKey.COMMENT);
    }

    /**
     * 添加收藏数 +1
     * @param articleId 文章id
     * @return true 成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Retry(name = "article-hot-info-api")
    public Boolean addStar(String articleId) {
        Long oldValue = articleHotMapper.selectStarNumByArticleId(articleId);
        return articleHotOperation(oldValue,articleId,OperatorEnum.INCREMENT, ArticleHotFactory.ArticleHotKey.STAR);
    }

    /**
     * 添加点赞数 +1
     * @param articleId 文章id
     * @return true 成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Retry(name = "article-hot-info-api")
    public Boolean addLiked(String articleId) {
        Long oldValue = articleHotMapper.selectLikedNumByArticleId(articleId);
        return articleHotOperation(oldValue,articleId,OperatorEnum.INCREMENT, ArticleHotFactory.ArticleHotKey.LIKED);
    }

    /**
     * 减少评论数 -1
     * @param articleId 文章id
     * @return true 成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Retry(name = "article-hot-info-api")
    public Boolean cancelComment(String articleId) {
        Long oldValue = articleHotMapper.selectCommentNumByArticleId(articleId);
        return articleHotOperation(oldValue,articleId,OperatorEnum.DECREMENT, ArticleHotFactory.ArticleHotKey.COMMENT);
    }

    /**
     * 减少收藏数 -1
     * @param articleId 文章id
     * @return true 成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Retry(name = "article-hot-info-api")
    public Boolean cancelStar(String articleId) {
        Long oldValue = articleHotMapper.selectStarNumByArticleId(articleId);
        return articleHotOperation(oldValue,articleId,OperatorEnum.DECREMENT, ArticleHotFactory.ArticleHotKey.STAR);
    }

    /**
     * 减少点赞数 -1
     * @param articleId 文章id
     * @return true 成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Retry(name = "article-hot-info-api")
    public Boolean cancelLiked(String articleId) {
        Long oldValue = articleHotMapper.selectLikedNumByArticleId(articleId);
        return articleHotOperation(oldValue,articleId,OperatorEnum.DECREMENT, ArticleHotFactory.ArticleHotKey.LIKED);
    }

    /**
     * 通用操作方法
     * @param oldValue 旧值
     * @param articleId 文章id
     * @param operator 运算操作
     * @param articleHotKey 文章热度列表 评论 收藏 点赞
     * @return true 操作成功
     */
    public boolean articleHotOperation(long oldValue, String articleId, OperatorEnum operator, ArticleHotFactory.ArticleHotKey articleHotKey){
        long operationValue = OperatorFactory.getOperation(operator).orElseThrow().apply(oldValue);
        ArticleHot articleHot = ArticleHotFactory.getArticleHotSaveValue(articleHotKey, operationValue).orElseThrow();
        int rows = articleHotMapper.update(articleHot, getUpdateWrapper(articleHotKey.getColumnName(),oldValue,articleId));
        if(rows <= 0)
            throw new RuntimeException();
//        rabbitTemplate.convertAndSend(ArticleHotKey.RABBITMQ_EXCHANGE_NAME,ArticleHotKey.RABBITMQ_EXCHANGE_KEY_COLUMN,articleId);
        return true;
    }

    private UpdateWrapper<ArticleHot> getUpdateWrapper(String column, Long oldValue, String articleId){
        UpdateWrapper<ArticleHot> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("article_id",articleId)
                .eq(column,oldValue);
        return updateWrapper;
    }
}
