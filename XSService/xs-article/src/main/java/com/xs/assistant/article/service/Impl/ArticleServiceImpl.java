package com.xs.assistant.article.service.Impl;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.article.Article;
import com.xs.DAO.DO.article.ArticleContext;
import com.xs.DAO.VO.article.ArticleVO;
import com.xs.assistant.article.DAO.ArticleMongodbRepository;
import com.xs.assistant.article.DAO.ArticleSearchDAO;
import com.xs.assistant.article.service.ArticleService;
import com.xs.assistant.article.service.remote.ESArticleRemoteService;
import com.xs.assistant.redis.util.RedisUtil;
import com.xs.assistant.util.Impl.UIDCodeUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 不推荐使用此类
 * 待更新、扩展
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    final ArticleMongodbRepository articleRepository;
    final ArticleSearchDAO articleDAO;
    final UIDCodeUtil codeUtil;
    final RedisUtil redisUtil;
    final ESArticleRemoteService esArticleRemoteService;

    private static final Long DEFALUT_TIME = 360L;
    private static final Long DEFALUT_HOT_TIME = 1200L;
    private static final String REDIS_ARTICLE_ID_KEY = "articleById:";

    public ArticleServiceImpl(ArticleMongodbRepository articleRepository, ArticleSearchDAO articleDAO, UIDCodeUtil codeUtil, RedisUtil redisUtil, ESArticleRemoteService esArticleRemoteService) {
        this.articleRepository = articleRepository;
        this.articleDAO = articleDAO;
        this.codeUtil = codeUtil;
        this.redisUtil = redisUtil;
        this.esArticleRemoteService = esArticleRemoteService;
    }

    /**
     * 获取对应文章
     * @param page 页数
     * @param size 大小
     * @return 文章列表
     */
    @Override
    @CircuitBreaker(name = "article-mongodb",fallbackMethod = "mongodbFail")
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<List<ArticleVO>> getArticles(int page, int size) {
        Page<ArticleContext> all = articleRepository.findAll(pageSet(page, size));
        esArticleRemoteService.insertArticleArray(all.stream().toList());
        return ResponseResult.success(all.map(articleMongoDO -> new ArticleVO(articleMongoDO,null)).stream().toList());
    }

    /**
     * 根据文章id获取文章
     * @param articleId 文章id
     * @return 文章
     */
    @Override
    @CircuitBreaker(name = "article-mongodb",fallbackMethod = "mongodbFail")
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<ArticleVO> findArticleByArticleId(String articleId) {
        if (redisUtil.hasHashKey(REDIS_ARTICLE_ID_KEY,articleId))
            return ResponseResult.success((ArticleVO) redisUtil.getHash(REDIS_ARTICLE_ID_KEY,articleId));
        Optional<ArticleContext> article = articleRepository.findById(articleId);
        return article.map(articleMongoDO -> findArticleById(articleMongoDO)
                .map((articleResult)->{
                    redisUtil.setHash(REDIS_ARTICLE_ID_KEY,articleId,articleResult,DEFALUT_TIME);
                    return ResponseResult.success(articleResult);
                }).orElseGet(this::articleNull))
                .orElseGet(this::articleNull);
    }

    /**
     * 根据文章id集合获取对应的所有文章
     * @param articleIds 文章id集合
     * @return 文章列表
     */
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

    /**
     * 根据关键字来查找对应标题的文章
     * @param title
     * @param page
     * @param size
     * @return
     */
    @Override
    @CircuitBreaker(name = "article-mongodb",fallbackMethod = "mongodbFail")
    @SuppressWarnings("all")
    public ResponseResult<List<ArticleVO>> findArticleByTitle(String title,int page, int size) {
        PageRequest pageRequest = pageSet(page,size);
        Page<ArticleContext> articles = articleRepository.findAllByTitleLike(title,pageRequest);
        return articleCollectionResult(articles);
    }

    /**
     * 根据关键字来查找对应副标题的文章
     * @param title 关键字
     * @param page 页数
     * @param size 大小
     * @return 文章列表
     */
    @Override
    @CircuitBreaker(name = "article-mongodb",fallbackMethod = "mongodbFail")
    public ResponseResult<List<ArticleVO>> findArticleBySubTitle(String title, int page, int size) {
        PageRequest pageRequest = pageSet(page,size);
        Page<ArticleContext> articles = articleRepository.findAllBySubTitleLike(title,pageRequest);
        return articleCollectionResult(articles);
    }

    /**
     * 根据关键字查询对应的标题和副标题的文章
     * @param title 关键字
     * @param page 页数
     * @param size 大小
     * @return 文章列表
     */
    @Override
    public ResponseResult<List<ArticleVO>> findArticleByTitleOrSubTitle(String title, int page, int size) {
        PageRequest pageRequest = pageSet(page,size);
        Page<ArticleContext> articles = articleRepository.findByTitleOrSubTitleContainingIgnoreCase(title,pageRequest);
        return articleCollectionResult(articles);
    }

    /**
     * 设置分页
     * @param page 页数
     * @param size 大小
     * @return page request
     */
    private PageRequest pageSet(int page,int size){
        return PageRequest.of(Math.max(page,0),Math.max(size,1));
    }

    /**
     * 将分页查询出来的数据 重新包装
     * @param articles Page<文章>
     * @return 文章列表
     */
    private ResponseResult<List<ArticleVO>> articleCollectionResult(Page<ArticleContext> articles){
        if (articles.isEmpty())
            return articleNull();
        List<ArticleVO> result = articles.stream()
                .map(article -> findArticleById(article).orElseThrow(NullPointerException::new)).toList();
        return ResponseResult.success(result);
    }

    /**
     * 根据id去mysql中查找对应的文章
     * @param article 文章
     * @return 文章
     */
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
