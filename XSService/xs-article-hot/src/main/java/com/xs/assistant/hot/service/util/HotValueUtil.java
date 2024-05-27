package com.xs.assistant.hot.service.util;

import com.xs.DAO.DO.article.ArticleHot;
import org.springframework.stereotype.Component;

@Component
public class HotValueUtil {
    /**
     * 计算文章热度值
     * 文章热度公式
     * 热度 = (文章评论数、阅读数、点赞数 + 作者影响因子) / (小说发布时长 + 1)^重力因子
     * @param articleHot 文章热度
     * @param gravity 重力值 当热门文章多则可以将此值调高 反之则调低
     * @return 热度值
     */
    public double calculateHotValue(ArticleHot articleHot,int gravity){
        double hot;
        double duration = (double)(System.currentTimeMillis() - articleHot.getCreateTime().getTime()) / 1000;
        long weight = articleHot.getCommentNum() + articleHot.getStarNum() + articleHot.getLikedNum();
        hot = Math.pow((duration + 1),gravity) / weight;
        return hot;
    }
}
