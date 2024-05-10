package com.xs.assistant.search.Service;

import com.xs.assistant.util.ElasticsearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class ESService {
    @Autowired
    protected ElasticsearchUtil elasticsearchUtil;
    protected static final String ES_ARTICLE_INDEX_NAME = "article";
}
