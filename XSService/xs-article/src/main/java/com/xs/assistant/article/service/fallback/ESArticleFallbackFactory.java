package com.xs.assistant.article.service.fallback;

import com.xs.DAO.DO.article.ArticleContext;
import com.xs.assistant.article.service.remote.ESArticleRemoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ESArticleFallbackFactory implements FallbackFactory<ESArticleRemoteService> {

    ESArticleRemoteService esFallback = new ESArticleRemoteService() {
        @Override
        public Boolean insertArticleArray(List<ArticleContext> articles) {
            return false;
        }

        @Override
        public List<ArticleContext> getArticlesAll() {
            return List.of();
        }

        @Override
        public List<ArticleContext> getArticlesByPage(Integer page, Integer size) {
            return List.of();
        }

        @Override
        public List<ArticleContext> getArticlesQuery(Integer page, Integer size, String field, String target) {
            return List.of();
        }

        @Override
        public List<ArticleContext> getArticleAllQuery(String target, Integer page, Integer size) {
            return List.of();
        }

        @Override
        public List<ArticleContext> getArticleScoreQuery(String field, String target, Integer page, Integer size) {
            return List.of();
        }

        @Override
        public List<ArticleContext> getArticleByTargetOrderHot(String target, Integer page, Integer size) {
            return List.of();
        }

        @Override
        public List<ArticleContext> getArticlesByTargetFindAuthorId(String authorId, Integer page, Integer size) {
            return List.of();
        }
    };

    @Override
    public ESArticleRemoteService create(Throwable cause) {
        log.error("XS-Article-Search 调用异常: {}",cause.getMessage(),cause);
        return esFallback;
    }
}
