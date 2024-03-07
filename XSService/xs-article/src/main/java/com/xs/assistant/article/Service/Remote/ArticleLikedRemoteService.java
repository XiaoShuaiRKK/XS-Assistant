package com.xs.assistant.article.Service.Remote;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.liked.DO.LikedDO;
import jakarta.validation.constraints.Min;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "XS-ARTICLE-LIKED",path = "/xs_assistant/liked")
public interface ArticleLikedRemoteService {
    @PostMapping("/liked")
    ResponseResult<Integer> likedByAccount(@RequestParam("articleId")String articleId,
                                                  @RequestParam("accountId")String accountId);

    @PostMapping("/unLiked")
    ResponseResult<Integer> unlikedByAccount(@RequestParam("articleId")String articleId,
                                                    @RequestParam("accountId")String accountId);

    @GetMapping("/top/getTop")
    ResponseResult<List<LikedDO>> getTop(@RequestParam("count") Long count);
}
