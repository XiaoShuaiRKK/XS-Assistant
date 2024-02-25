package com.xs.assistant.article.Service.Impl;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.article.DO.Article;
import com.xs.DAO.article.DO.ArticleMongoDO;
import com.xs.DAO.article.VO.ArticleVO;
import com.xs.assistant.article.DAO.ArticleDAO;
import com.xs.assistant.article.DAO.ArticleRepository;
import com.xs.assistant.article.Service.ArticleService;
import com.xs.assistant.util.Impl.CommonUtil;
import com.xs.assistant.util.Impl.UIDCodeUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleDAO articleDAO;
    @Autowired
    UIDCodeUtil codeUtil;

    @Override
    @CircuitBreaker(name = "article-mongodb",fallbackMethod = "mongodbFail")
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<ArticleVO> findArticleByArticleId(String articleId) {
        Optional<ArticleMongoDO> article = articleRepository.findById(articleId);
        return article.map(articleMongoDO -> findArticleById(articleMongoDO)
                .map(ResponseResult::success).orElseGet(this::articleNull))
                .orElseGet(this::articleNull);
    }

    @Override
    @CircuitBreaker(name = "article-mongodb",fallbackMethod = "mongodbFail")
    @SuppressWarnings("all")
    public ResponseResult<List<ArticleVO>> findArticleByTitle(String title,int page, int size) {
        PageRequest pageRequest = PageRequest.of(Math.max(page,0), size);
        Page<ArticleMongoDO> articles = articleRepository.findAllByTitleLike(title,pageRequest);
        if(articles.isEmpty())
            return articleNull();
        List<ArticleVO> articleVOS = articles.stream()
                .map(articleMongoDO -> findArticleById(articleMongoDO).get()).toList();
        return ResponseResult.success(articleVOS);
    }

    private Optional<ArticleVO> findArticleById(ArticleMongoDO article){
         Optional<Article> articleInfo = Optional.ofNullable(articleDAO.selectArticleByArticleId(article.getId()));
        return articleInfo.map(value -> new ArticleVO(article, value));
    }

    private <T> ResponseResult<T> articleNull(){
        return ResponseResult.success(null,"未找到文章");
    }

    @Override
    @CircuitBreaker(name = "article-mongodb",fallbackMethod = "mongodbFail")
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Boolean> addArticle(ArticleVO article) {
        String articleId = codeUtil.createCodeWithArticle(articleRepository.count());
        ArticleMongoDO articleMongo = article.getContext();
        Article articleInfo = article.getArticle();
        articleInfo.setArticleId(articleId);
        articleMongo.setId(articleId);
        articleRepository.insert(articleMongo);
        int rs = articleDAO.insertArticle(articleInfo);
        if(rs <= 0)
            throw new RuntimeException();
        return ResponseResult.success(true);
    }

    private <T extends Serializable> ResponseResult<T> mongodbFail(Exception e){
        return ResponseResult.error(null,"操作失败,请稍后重试");
    }
}
