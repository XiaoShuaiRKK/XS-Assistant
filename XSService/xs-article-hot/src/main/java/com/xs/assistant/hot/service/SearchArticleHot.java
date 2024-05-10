package com.xs.assistant.hot.service;

import java.util.List;
import java.util.Set;

public interface SearchArticleHot {
    Set<Object> getHotTop();
    Set<Object> getHotTop(long s,long e);
    Set<Object> getHotTop(String target);
}
