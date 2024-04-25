package com.xs.assistant.article.Service.Impl;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.article.Article;
import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.VO.article.ArticleVO;
import com.xs.assistant.article.DAO.ArticleDAO;
import com.xs.assistant.article.DAO.ArticleRepository;
import com.xs.assistant.article.Service.ArticleService;
import com.xs.assistant.article.Service.Remote.ESArticleRemoteService;
import com.xs.assistant.redis.Util.RedisUtil;
import com.xs.assistant.util.Impl.UIDCodeUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleDAO articleDAO;
    @Autowired
    UIDCodeUtil codeUtil;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    ESArticleRemoteService esArticleRemoteService;


    private static final Long DEFALUT_TIME = 360L;
    private static final Long DEFALUT_HOT_TIME = 1200L;
    private static final String REDIS_ARTICLE_ID_KEY = "articleById:";

    @Override
    @CircuitBreaker(name = "article-mongodb",fallbackMethod = "mongodbFail")
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<List<ArticleVO>> getArticles(int page, int size) {
        Page<ArticleContext> all = articleRepository.findAll(pageSet(page, size));
        esArticleRemoteService.insertArticleArray(all.stream().toList());
        return ResponseResult.success(all.map(articleMongoDO -> new ArticleVO(articleMongoDO,null)).stream().toList());
    }

    @Override
    @CircuitBreaker(name = "article-mongodb",fallbackMethod = "mongodbFail")
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<ArticleVO> findArticleByArticleId(String articleId) {
        if (redisUtil.hasKey(REDIS_ARTICLE_ID_KEY,articleId))
            return ResponseResult.success((ArticleVO) redisUtil.getHash(REDIS_ARTICLE_ID_KEY,articleId));
        Optional<ArticleContext> article = articleRepository.findById(articleId);
        return article.map(articleMongoDO -> findArticleById(articleMongoDO)
                .map((articleResult)->{
                    redisUtil.setHash(REDIS_ARTICLE_ID_KEY,articleId,articleResult,DEFALUT_TIME);
                    return ResponseResult.success(articleResult);
                }).orElseGet(this::articleNull))
                .orElseGet(this::articleNull);
    }

    @Override
    @CircuitBreaker(name = "article-mongodb",fallbackMethod = "mongodbFail")
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<List<ArticleVO>> findArticlesByArticleIds(List<String> articleIds) {
        List<Article> articles = articleDAO.selectArticlesByArticleId(articleIds)
                .stream()
                .sorted(Comparator.comparing(Article::getArticleId).reversed())
                .toList();
        List<ArticleContext> articleMongos = articleRepository.findAllById(articleIds)
                .stream()
                .sorted(Comparator.comparing(ArticleContext::getId).reversed())
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
        Page<ArticleContext> articles = articleRepository.findAllByTitleLike(title,pageRequest);
        return articleCollectionResult(articles);
    }

    @Override
    @CircuitBreaker(name = "article-mongodb",fallbackMethod = "mongodbFail")
    public ResponseResult<List<ArticleVO>> findArticleBySubTitle(String title, int page, int size) {
        PageRequest pageRequest = pageSet(page,size);
        Page<ArticleContext> articles = articleRepository.findAllBySubTitleLike(title,pageRequest);
        return articleCollectionResult(articles);
    }

    @Override
    public ResponseResult<List<ArticleVO>> findArticleByTitleOrSubTilte(String title, int page, int size) {
        PageRequest pageRequest = pageSet(page,size);
        Page<ArticleContext> articles = articleRepository.findByTitleOrSubTitleContainingIgnoreCase(title,pageRequest);
        return articleCollectionResult(articles);
    }

    private PageRequest pageSet(int page,int size){
        return PageRequest.of(Math.max(page,0),Math.max(size,1));
    }

    private ResponseResult<List<ArticleVO>> articleCollectionResult(Page<ArticleContext> articles){
        if (articles.isEmpty())
            return articleNull();
        List<ArticleVO> result = articles.stream()
                .map(article -> findArticleById(article).orElseThrow(NullPointerException::new)).toList();
        return ResponseResult.success(result);
    }

    private Optional<ArticleVO> findArticleById(ArticleContext article){
         Optional<Article> articleInfo = Optional.ofNullable(articleDAO.selectArticleByArticleId(article.getId()));
        return articleInfo.map(value -> new ArticleVO(article, value));
    }

    private <T> ResponseResult<T> articleNull(){
        return ResponseResult.success(null,"未找到文章");
    }



    private <T> ResponseResult<T> mongodbFail(Exception e){
        return ResponseResult.error(null,"操作失败,请稍后重试");
    }
}
