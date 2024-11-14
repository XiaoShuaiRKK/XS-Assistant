package com.xs.assistant.article.DAO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xs.DAO.DO.article.ArticleState;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleStateMapper extends BaseMapper<ArticleState> {
    @Select("select id,state_name from article_state")
    List<ArticleState> selectAll();
}
