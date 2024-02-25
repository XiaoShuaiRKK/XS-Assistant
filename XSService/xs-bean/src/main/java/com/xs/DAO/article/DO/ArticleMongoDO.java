package com.xs.DAO.article.DO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

//将该对象声明为要持久化到MongoDB中的文档
@Document(value = "article")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleMongoDO implements Serializable {
    @Id
    private String id;
    @NotEmpty(message = "context cannot be empty")
    private String context;
    @NotEmpty(message = "title cannot be empty")
    private String title;
    @NotEmpty(message = "sub title cannot be empty")
    private String subTitle;
}
