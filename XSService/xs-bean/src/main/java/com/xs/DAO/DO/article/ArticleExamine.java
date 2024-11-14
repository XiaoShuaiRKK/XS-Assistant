package com.xs.DAO.DO.article;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
@TableName("article_examine_log")
public class ArticleExamine {
    @TableId
    private int id;
    @TableField("article_id")
    private String articleId;
    @TableField("examine_state")
    private Integer examineState;
    @TableField("description")
    private String description;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
}
