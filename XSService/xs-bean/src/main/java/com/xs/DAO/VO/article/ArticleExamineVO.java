package com.xs.DAO.VO.article;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class ArticleExamineVO {
    private int id;
    private String articleId;
    private Integer examineState;
    private String examineName;
    private String description;
    private Date createTime;
    private Date updateTime;
}
