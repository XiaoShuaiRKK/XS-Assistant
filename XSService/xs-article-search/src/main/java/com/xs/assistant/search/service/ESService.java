package com.xs.assistant.search.service;

import com.xs.assistant.util.ElasticsearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class ESService {
    @Autowired
    protected ElasticsearchUtil elasticsearchUtil;
}
