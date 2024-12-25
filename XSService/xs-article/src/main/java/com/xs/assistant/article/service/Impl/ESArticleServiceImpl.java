package com.xs.assistant.article.service.Impl;

import com.xs.DAO.DO.article.Article;
import com.xs.DAO.mapper.ArticleContextMapper;
import com.xs.DAO.ResponseResult;
import com.xs.DAO.VO.article.ArticleContextVO;
import com.xs.assistant.article.DAO.ArticleDAO;
import com.xs.assistant.article.aspect.annotation.ResultPackage;
import com.xs.assistant.article.service.ArticleStateService;
import com.xs.assistant.article.service.ESArticleService;
import com.xs.assistant.article.service.remote.AccountInfoService;
import com.xs.assistant.article.service.remote.ESArticleRemoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ESArticleServiceImpl implements ESArticleService {
    final ESArticleRemoteService esArticleRemoteService;
    final AccountInfoService accountInfoService;
    final ArticleStateService articleStateService;

    public ESArticleServiceImpl(ESArticleRemoteService esArticleRemoteService, AccountInfoService accountInfoService, ArticleStateService articleStateService) {
        this.esArticleRemoteService = esArticleRemoteService;
        this.accountInfoService = accountInfoService;
        this.articleStateService = articleStateService;
    }

    /**
     * 查询所有的文章
     * @param page 页数
     * @param size 大小
     * @return 文章列表
     */
    @Override
    @ResultPackage
    public ResponseResult<List<ArticleContextVO>> getArticlesByPage(int page,int size) {
        List<ArticleContextVO> list = esArticleRemoteService.getArticlesByPage(page, size).stream()
                .map(a -> ArticleContextMapper.INSTANCE.articleToArticleContextVO(a, new Article())).toList();
        list.forEach(vo -> {
            vo.setAuthorName(accountInfoService.getCustomerName(vo.getAuthorId()).getData());
            vo.setStateName(articleStateService.findById(vo.getStateId()).getStateName());
        });
        return ResponseResult.success(list);
    }

    /**
     * 根据对应关键字查询文章
     * @param target 关键字
     * @param page 页数
     * @param size 大小
     * @return 文章列表
     */
    @Override
    @ResultPackage
    public ResponseResult<List<ArticleContextVO>> getArticlesByAllField(String target, int page, int size) {
        List<ArticleContextVO> list = esArticleRemoteService.getArticleAllQuery(target, page, size).stream()
                .map(a -> ArticleContextMapper.INSTANCE.articleToArticleContextVO(a, new Article())).toList();
        list.forEach(vo -> {
            vo.setAuthorName(accountInfoService.getCustomerName(vo.getAuthorId()).getData());
            vo.setStateName(articleStateService.findById(vo.getStateId()).getStateName());
        });
        return ResponseResult.none(list);
    }

    /**
     * 根据关键字查找对应字段的文章
     * 按照匹配程度排序
     * @param field 字段
     * @param target 关键字
     * @param page 页数
     * @param size 大小
     * @return 文章列表
     */
    @Override
    @ResultPackage
    public ResponseResult<List<ArticleContextVO>> getArticlesByScore(String field, String target, int page, int size) {
        return ResponseResult.none(esArticleRemoteService.getArticleScoreQuery(field,target,page,size).stream()
                .map(a -> ArticleContextMapper.INSTANCE.articleToArticleContextVO(a,new Article())).toList());
    }

    @Override
    public List<ArticleContextVO> getArticlesByTargetFindIdNumber(String idNumber, int page, int size) {
        List<ArticleContextVO> articleContextVOS = esArticleRemoteService.getArticlesByTargetFindAuthorId(idNumber, page, size).stream()
                .map(a -> ArticleContextMapper.INSTANCE.articleToArticleContextVO(a, new Article())).toList();
        articleContextVOS.forEach(vo -> {
            vo.setAuthorName(accountInfoService.getCustomerName(vo.getAuthorId()).getData());
            vo.setStateName(articleStateService.findById(vo.getStateId()).getStateName());
        });
        return articleContextVOS;
    }

    @Override
    public List<ArticleContextVO> getArticleByTargetOrderHot(String target, int page, int size) {
        List<ArticleContextVO> articleContextVOS = esArticleRemoteService.getArticleByTargetOrderHot(target, page, size).stream()
                .map(a -> ArticleContextMapper.INSTANCE.articleToArticleContextVO(a, new Article())).toList();
        articleContextVOS.forEach(vo -> {
            vo.setAuthorName(accountInfoService.getCustomerName(vo.getAuthorId()).getData());
            vo.setStateName(articleStateService.findById(vo.getStateId()).getStateName());
        });
        return articleContextVOS;
    }
}
