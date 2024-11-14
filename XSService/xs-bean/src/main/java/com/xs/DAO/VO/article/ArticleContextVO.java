package com.xs.DAO.VO.article;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleContextVO implements Serializable {
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
    private String authorName;
    private Integer stateId;
    private String stateName;
    private String image;
    private String background;
    private String description;
}
