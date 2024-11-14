package com.xs.assistant.article.service.Impl;

import com.xs.DAO.DO.article.ArticleState;
import com.xs.assistant.article.DAO.ArticleStateMapper;
import com.xs.assistant.article.service.ArticleStateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleStateServiceImpl implements ArticleStateService {
    private List<ArticleState> cache;

    final ArticleStateMapper articleStateMapper;

    public ArticleStateServiceImpl(ArticleStateMapper articleStateMapper) {
        this.articleStateMapper = articleStateMapper;
        cache = articleStateMapper.selectAll();
    }


    @Override
    public List<ArticleState> findAll() {
        if (cache == null) {
            cache = articleStateMapper.selectAll();
        }
        return cache;
    }

    @Override
    public ArticleState findById(Integer id) {
        if (cache == null) {
            findAll();
        }
        return cache.get(id - 1);
    }
}
