package com.xs.assistant.article.Service.Fallback;

import com.xs.DAO.DO.article.ArticleMongoDO;
import com.xs.assistant.article.Service.Remote.ESArticleRemoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Slf4j
public class ESArticleFallbackFactory implements FallbackFactory<ESArticleRemoteService> {

    ESArticleRemoteService esFallback = new ESArticleRemoteService() {
        @Override
        public Boolean insertArticleArray(List<ArticleMongoDO> articles) {
            return false;
        }

        @Override
        public List<ArticleMongoDO> getArticlesAll() {
            return List.of();
        }
    };

    @Override
    public ESArticleRemoteService create(Throwable cause) {
        log.error("XS-Article-Search 调用异常: {}",cause.getMessage(),cause);
        return esFallback;
    }
}
