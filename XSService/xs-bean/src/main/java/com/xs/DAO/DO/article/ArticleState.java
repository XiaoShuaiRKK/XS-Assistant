package com.xs.DAO.DO.article;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("article_state")
public class ArticleState {
    @TableId
    private int id;
    @TableField("state_name")
    private String stateName;
}
