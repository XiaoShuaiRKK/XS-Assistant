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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.*;

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
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<List<ArticleVO>> findArticlesByAritcleIds(List<String> articleIds) {
        List<Article> articles = articleDAO.selectArticlesByArticleId(articleIds)
                .stream()
                .sorted(Comparator.comparing(Article::getArticleId).reversed())
                .toList();
        List<ArticleMongoDO> articleMongos = articleRepository.findAllById(articleIds)
                .stream()
                .sorted(Comparator.comparing(ArticleMongoDO::getId).reversed())
                .toList();
        List<ArticleVO> articleVOS = new LinkedList<>();
        for(int i=0;i < articleIds.size();i++)
            articleVOS.add(new ArticleVO(articleMongos.get(i),articles.get(i)));
        return ResponseResult.success(articleVOS);
    }

    @Override
    @CircuitBreaker(name = "article-mongodb",fallbackMethod = "mongodbFail")
    @SuppressWarnings("all")
    public ResponseResult<List<ArticleVO>> findArticleByTitle(String title,int page, int size) {
        PageRequest pageRequest = pageSet(page,size);
        Page<ArticleMongoDO> articles = articleRepository.findAllByTitleLike(title,pageRequest);
        return articleCollectionResult(articles);
    }

    @Override
    public ResponseResult<List<ArticleVO>> findArticleByTitleAndSubTitle(String title, int page, int size) {
        PageRequest pageRequest = pageSet(page,size);
        Page<ArticleMongoDO> articles = articleRepository.findAllByTitleLikeOrSubTitleLike(title,pageRequest);
        return articleCollectionResult(articles);
    }

    private PageRequest pageSet(int page,int size){
        return PageRequest.of(Math.max(page,0),size);
    }

    private ResponseResult<List<ArticleVO>> articleCollectionResult(Page<ArticleMongoDO> articles){
        if (articles.isEmpty())
            return articleNull();
        List<ArticleVO> result = articles.stream()
                .map(article -> findArticleById(article).orElseThrow(NullPointerException::new)).toList();
        return ResponseResult.success(result);
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
