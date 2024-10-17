package com.xs.assistant.hot.DAO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xs.DAO.DO.article.ArticleHot;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ArticleHotMapper extends BaseMapper<ArticleHot> {
    @Select("SELECT hot_id,article_hot.article_id,comment_num,star_num,liked_num,note.create_time FROM article_hot,note" +
            " WHERE article_hot.article_id = note.article_id AND article_hot.article_id = #{articleId}")
    ArticleHot selectByArticleId(@Param("articleId")String articleId);
    @Select("SELECT hot_id,article_hot.article_id,comment_num,star_num,liked_num,note.create_time FROM article_hot,note" +
            " WHERE article_hot.article_id = note.article_id")
    IPage<ArticleHot> selectPageAll(Page<?> page);
    @Select("SELECT comment_num FROM article_hot WHERE article_id = #{articleId}")
    Long selectCommentNumByArticleId(@Param("articleId") String articleId);
    @Select("SELECT star_num FROM article_hot WHERE article_id = #{articleId}")
    Long selectStarNumByArticleId(@Param("articleId") String articleId);
    @Select("SELECT liked_num FROM article_hot WHERE article_id = #{articleId}")
    Long selectLikedNumByArticleId(@Param("articleId") String articleId);
//    Long incrementCommentNum(@Param("articleId") String articleId, @Param("version")Long version);
}
