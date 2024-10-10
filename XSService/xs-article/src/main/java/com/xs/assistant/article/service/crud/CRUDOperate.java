package com.xs.assistant.article.service.crud;

import java.util.List;
import java.util.concurrent.Future;

public interface CRUDOperate<T> {
    Future<Boolean> insertArticle(T entity);
    Future<Boolean> batchInsert(List<T> entities);
    Future<Boolean> deleteArticle(T entity);
    Future<Boolean> deleteArticles(List<T> entities);
    Future<Boolean> updateArticle(T entity);
    Future<Boolean> updateArticles(List<T> entities);
    Future<Boolean> rollbackInsert(T entity);
    Future<Boolean> rollbackBatchInsert(List<T> entities);
    Future<Boolean> rollbackDelete(T entity);
    Future<Boolean> rollbackDeletes(List<T> entities);
    Future<Boolean> rollbackUpdate(T entity);
    Future<Boolean> rollbackUpdates(List<T> entities);
}
