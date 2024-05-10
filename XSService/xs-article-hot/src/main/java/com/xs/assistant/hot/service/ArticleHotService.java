package com.xs.assistant.hot.service;

public interface ArticleHotService {
    Boolean addComment(String articleId);
    Boolean addStar(String articleId);
    Boolean addLiked(String articleId);
    Boolean cancelComment(String articleId);
    Boolean cancelStar(String articleId);
    Boolean cancelLiked(String articleId);
}
