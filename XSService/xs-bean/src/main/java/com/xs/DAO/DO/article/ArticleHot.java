package com.xs.DAO.DO.article;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("article_hot")
public class ArticleHot implements Serializable {
    @Id
    private Long hotId;
    private String articleId;
    private Long commentNum;
    private Long starNum;
    private Long likedNum;
    @TableField("create_time")
    private Date createTime;
}
