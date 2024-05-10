package com.xs.assistant.hot.service.schedule;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.DO.article.ArticleHot;
import com.xs.DAO.repository.ElasticRepositoryKeyEnum;
import com.xs.assistant.hot.DAO.ArticleHotKey;
import com.xs.assistant.hot.DAO.ArticleHotMapper;
import com.xs.assistant.hot.service.util.HotValueUtil;
import com.xs.assistant.redis.Util.RedisUtil;
import com.xs.assistant.util.ElasticsearchUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
@Slf4j
public class ScheduledArticleHot {
    private static final Long PAGE_DEFAULT_SIZE = 100000L;

    final ElasticsearchUtil elasticsearchUtil;
    final ArticleHotMapper articleHotMapper;
    final HotValueUtil hotValueUtil;
    final RedisUtil redisUtil;

    public ScheduledArticleHot(ElasticsearchUtil elasticsearchUtil, ArticleHotMapper articleHotMapper, HotValueUtil hotValueUtil, RedisUtil redisUtil) {
        this.elasticsearchUtil = elasticsearchUtil;
        this.articleHotMapper = articleHotMapper;
        this.hotValueUtil = hotValueUtil;
        this.redisUtil = redisUtil;
    }

    @Scheduled(fixedRate = 1000 * 60 * 3)
    public void runArticleHotCalculate(){
        log.info("scheduled run article hot calculate...");
        try {
            long page = 0L;
            IPage<ArticleHot> articleHotPage;
            do {
                articleHotPage = articleHotMapper.selectPageAll(Page.of(page,PAGE_DEFAULT_SIZE));
                page = articleHotPage.getCurrent() + 1;
                BulkRequest bulkRequest = new BulkRequest();
                articleHotPage.getRecords().forEach(articleHot -> bulkRequest.add(
                        elasticsearchUtil.getBulkUpdateRequest(
                                ArticleHotKey.ES_ARTICLE_INDEX_NAME,
                                articleHot.getArticleId(),
                                ElasticRepositoryKeyEnum.ES_ARTICLE_COLUMN_HOT_KEY.getKey(),
                                hotValueUtil.calculateHotValue(articleHot,1)
                        )
                ));
                elasticsearchUtil.bulkUpdate(bulkRequest);
            }while (page < articleHotPage.getPages());
        }catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
