package com.xs.DAO.DO.article;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;

//将该对象声明为要持久化到MongoDB中的文档
@Document(value = "article")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleContext implements Serializable {
    @Id
    @MongoId
    private String id;
    @NotEmpty(message = "context cannot be empty")
    private String context;
    @NotEmpty(message = "title cannot be empty")
    private String title;
    @NotEmpty(message = "sub title cannot be empty")
    private String subTitle;
    @NotEmpty(message = "author id cannot be empty")
    @Length(min = 14,max = 14,message = "The id format is incorrect")
    private String authorId;
    private String description;
    private Integer stateId;
    private Double hot;
}
