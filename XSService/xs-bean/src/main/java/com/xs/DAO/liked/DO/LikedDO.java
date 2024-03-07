package com.xs.DAO.liked.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikedDO {
    private String articleId;
    private Long liked;
}
