package com.xs.assistant.hot.service.util;

import com.xs.DAO.DO.article.ArticleHot;
import org.springframework.stereotype.Component;

@Component
public class HotValueUtil {
    /**
     *
     * @param articleHot
     * @param gravity 重力值 当热门文章多则可以将此值调高 反之则调低
     * @return
     */
    public double calculateHotValue(ArticleHot articleHot,int gravity){
        double hot;
        double duration = (double)(System.currentTimeMillis() - articleHot.getCreateTime().getTime()) / 1000;
        long weight = articleHot.getCommentNum() + articleHot.getStarNum() + articleHot.getLikedNum();
        hot = Math.pow((duration + 1),gravity) / weight;
        return hot;
    }
}
