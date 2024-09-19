package com.xs.assistant.article.DAO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xs.DAO.DO.article.ArticleContext;
import org.mapstruct.Mapper;

@Mapper
public interface ArticleBatchRepository extends BaseMapper<ArticleContext> {
}
