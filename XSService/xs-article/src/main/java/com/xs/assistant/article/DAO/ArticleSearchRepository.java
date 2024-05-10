package com.xs.assistant.article.DAO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xs.DAO.DO.article.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleSearchRepository extends BaseMapper<Article> {
}
