package com.xs.assistant.article.service.schedule;

import com.xs.DAO.DO.article.ArticleContext;
import com.xs.assistant.article.DAO.ArticleMongodbRepository;
import com.xs.assistant.article.service.remote.ESArticleRemoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

/**
 * 已弃用
 */
@Configuration
@EnableScheduling
@Slf4j
public class ArticleMysqlToESSchedule {
    final ArticleMongodbRepository articleMongodbRepository;
    final ESArticleRemoteService remoteService;

    public ArticleMysqlToESSchedule(ArticleMongodbRepository articleMongodbRepository, ESArticleRemoteService remoteService) {
        this.articleMongodbRepository = articleMongodbRepository;
        this.remoteService = remoteService;
    }

    @Deprecated
//    @Scheduled(fixedRate = 1000 * 60)
    public void runMysqlToES(){
        log.info("ES Update Start...");
        List<ArticleContext> all = articleMongodbRepository.findAll();
        List<ArticleContext> articleContexts = all.stream().peek(articleContext -> {
            articleContext.setStateId(1);
            articleContext.setHot(0D);
        }).toList();
        remoteService.insertArticleArray(articleContexts);
    }
}
