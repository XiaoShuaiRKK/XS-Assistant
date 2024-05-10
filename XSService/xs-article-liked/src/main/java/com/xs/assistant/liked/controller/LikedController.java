package com.xs.assistant.liked.controller;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.liked.LikedDO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.xs.assistant.liked.service.*;

import java.util.List;

@Validated
@Slf4j
@RestController
@RequestMapping("/liked")
public class LikedController {

    @Autowired
    LikedService likedService;
    @Autowired
    LikedTopService likedTopService;

    @PostMapping("/liked")
    public ResponseResult<Integer> likedByAccount(@RequestParam(value = "articleId")
                                                      @NotEmpty(message = "Article ID cannot be empty")
                                                      @Length(min = 14,max = 14,message = "Please enter a valid article ID")
                                                      String articleId,
                                                  @RequestParam(value = "accountId")
                                                  @NotEmpty(message = "Account ID cannot be empty")
                                                  @Length(min = 14,max = 14,message = "Please enter a valid account ID")
                                                  String accountId){
        return likedService.likedByArticleId(articleId,accountId);
    }

    @PostMapping("/unLiked")
    public ResponseResult<Integer> unlikedByAccount(@RequestParam(value = "articleId")
                                                        @NotEmpty(message = "article id cannot be empty")
                                                        @Length(min = 14,max = 14,message = "Please enter a valid article ID")
                                                        String articleId,
                                                    @RequestParam(value = "accountId")
                                                    @NotEmpty(message = "article id cannot be empty")
                                                    @Length(min = 14,max = 14,message = "Please enter a valid account ID")
                                                    String accountId){
        return likedService.unLikedByArticleId(articleId,accountId);
    }

    @GetMapping("/top/getTop")
    public ResponseResult<List<LikedDO>> getTop(@RequestParam("count")
                                                    @Min(value = 1,message = "count can only be a positive number")
                                                    Long count){
        return likedTopService.getTop(count);
    }

}
