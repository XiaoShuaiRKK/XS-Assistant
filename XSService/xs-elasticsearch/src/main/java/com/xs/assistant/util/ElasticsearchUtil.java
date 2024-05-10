package com.xs.assistant.util;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.mapping.Property;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import com.xs.assistant.util.Impl.BeanFieldUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class ElasticsearchUtil {
    @Autowired
    ElasticsearchClient client;
    @Autowired
    RestHighLevelClient restHighLevelClient;
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    BeanFieldUtil beanFieldUtil;

    /**
     * 判断索引是否存在
     * @param indexName 索引
     * @return true则存在
     * @throws IOException IOException
     */
    public boolean hasIndex(String indexName) throws IOException {
        return client.indices().exists(d -> d.index(indexName)).value();
    }

    public boolean deleteIndex(String indexName){
        try {
            client.indices().delete(d -> d.index(indexName));
        } catch (IOException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 创建索引
     * @param indexName
     * @return
     */
    public boolean createIndex(String indexName){
        try {
            client.indices().create(c -> c.index(indexName));
        } catch (IOException e) {
            log.error("索引创建失败: {}",e.getMessage());
        }
        return true;
    }

    /**
     * 创建索引,不允许外部直接调用
     * @param indexName
     * @param mapping
     * @return
     */
    public boolean createIndex(String indexName, Map<String, Property> mapping){
        try {
            CreateIndexResponse indexResponse = client.indices().create(c -> {
                c.index(indexName).mappings(mappings -> mappings.properties(mapping));
                return c;
            });
            return indexResponse.acknowledged();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return false;
    }

    /**
     * 如果存在则先删除原本的再创建
     * @param indexName
     * @param map
     */
    public void ifCreateIndex(String indexName,Map<String,Property> map){
        try {
            if(hasIndex(indexName))
                deleteIndex(indexName);
            createIndex(indexName,map);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 新增数据
     * @param indexName
     * @param id
     * @param obj
     * @return
     */
    public boolean insertDocument(String indexName,String id,Object obj){
        try {
            if(!hasIndex(indexName))
                createIndex(indexName);
            client.index(i -> i.index(indexName).id(id).document(obj));
        } catch (IOException e) {
            log.error("ElasticSearch 数据插入异常: {}",e.getMessage());
            return false;
        }
        return true;
    }

    public <T> boolean insertDocuments(String indexName,List<T> array){
        List<BulkOperation> bulkOperations = new ArrayList<>();
        array.forEach(o -> bulkOperations.add(BulkOperation.of(b -> b.index(i -> i.document(o)))));
        try {
            client.bulk(b -> b.index(indexName).operations(bulkOperations));
        } catch (IOException e) {
            log.error("ElasticSearch 批量插入异常: {}",e.getMessage());
            return false;
        }
        return true;
    }

//    public boolean putMappingAddField(String index,String field,String fieldType){
//        try {
//            XContentBuilder mapping = XContentFactory.jsonBuilder()
//                    .startObject().startObject("properties")
//                    .startObject(field).field("type",fieldType)
//                    .endObject().endObject().endObject();
//            PutMappingRequest.Builder builder = new PutMappingRequest.Builder();
//            builder.index(index);
//            builder.source(new SourceField.Builder().includes(mapping.toString()).build());
//            client.indices().putMapping(builder.build());
//        } catch (IOException e) {
//            log.error(e.getMessage());
//            return false;
//        }
//        return true;
//    }

    /**
     * 查询数据
     * @param indexName
     * @param id
     * @param tClass
     * @return
     * @param <T>
     */
    public <T> GetResponse<T> searchDocument(String indexName,String id,Class<T> tClass){
        try {
            return client.get(g -> g.index(indexName).id(id), tClass);
        } catch (IOException e) {
            log.error("ElasticSearch 数据查询异常: {}",e.getMessage());
        }
        return null;
    }


    public <T> List<Hit<T>> searchDocumentsOrderSortQuery(String indexName,String field,String orderField,String target,Class<T> tClass){
        return searchDocumentsOrderSortQuery(indexName,field,orderField,SortOrder.Asc,target,0,10,tClass);
    }


    public <T> List<Hit<T>> searchDocumentsMaxScore(String indexName, String field, String target,
                                                    int page,int size, Class<T> tClass){
        try {
            return client.search(s -> s.index(indexName).query(
                    q -> q.bool(
                            b -> b.must(
                                    m -> m.match(
                                            match -> match.field(field)
                                    )
                            )
                    )
            ).from(page).size(size),tClass).hits().hits();
        } catch (IOException e) {
            log.error("Elasticsearch bool 查询异常: {}",e.getMessage());
        }
        return null;
    }

    public <T> List<Hit<T>> searchDocumentsMultiQuery(String indexName,String target,int page,int size,Class<T> tClass){
        try {
            return client.search(s -> s.index(indexName).query(
                    q -> q.multiMatch(
                            m -> m.fields(beanFieldUtil.getFieldsNameArray(tClass)).query(target)
                    )
            ).from(page).size(size),tClass).hits().hits();
        } catch (IOException e) {
            log.error("Elasticsearch Multi 查询异常: {}",e.getMessage());
        }
        return null;
    }

    /**
     * 排序查询数据
     * @param indexName 索引
     * @param field 字段
     * @param orderField 排列字段
     * @param sortOrder 排序
     * @param target 关键字
     * @param tClass 类型
     * @return
     * @param <T>
     */
    public <T> List<Hit<T>> searchDocumentsOrderSortQuery(String indexName, String field, String orderField,
                                            SortOrder sortOrder, String target,int page,int size,Class<T> tClass){
        try {
            SearchResponse<T> search = client.search(s -> s.index(indexName)
                    .query(q -> q.term(
                            t -> t.field(field).value(target)
                    ))
                    .from(page).size(size)
                    .sort(f -> f.field(o -> o.field(orderField).order(sortOrder))),tClass);
            return search.hits().hits();
        } catch (IOException e) {
            log.error("ElasticSearch 排序条件查询异常: {}",e.getMessage());
        }
        return null;
    }

    /**
     * 查询
     * @param indexName
     * @param field
     * @param target
     * @param tClass
     * @param page
     * @param size
     * @return
     * @param <T>
     */
    public <T> List<Hit<T>> searchDocumentsQuery(String indexName, String field,
                                                 String target, int page,int size,Class<T> tClass){
        try {
            SearchResponse<T> search = client.search(s -> s.index(indexName)
                    .query(q -> q.term(
                            t -> t.field(field).value(target)
                    ))
                    .from(page).size(size),tClass);
            return search.hits().hits();
        } catch (IOException e) {
            log.error("ElasticSearch 条件查询异常: {}",e.getMessage());
        }
        return null;
    }

    public <T> List<Hit<T>> searchDocuments(String indexName,int page,int size,Class<T> tClass){
        try {
            return client.search(s -> s.index(indexName).query(q -> q.matchAll(m -> m)).from(page).size(size),tClass)
                    .hits().hits();
        } catch (IOException e) {
            log.error("ElasticSearch index: {} 查询全部异常: {}",indexName,e.getMessage());
        }
        return null;
    }

    /**
     * 删除数据
     * @param indexName
     * @param id
     * @return
     */
    public boolean deleteDocument(String indexName,String id){
        try {
            client.delete(d -> d.index(indexName).id(id));
            return true;
        } catch (IOException e) {
            log.error("ElasticSearch 删除数据异常: {}",e.getMessage());
        }
        return false;
    }


    public <T> boolean updateByDocumentId(String index,String id,String field,T fieldValue){
        try {
            elasticsearchTemplate.update(getUpdateSingleField(index,id,field,fieldValue));
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean bulkUpdate(BulkRequest bulkRequest){
        try {
            restHighLevelClient.bulk(bulkRequest,RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public  <T> org.elasticsearch.action.update.UpdateRequest getBulkUpdateRequest(String index, String id, String field, T fieldValue){
        return new org.elasticsearch.action.update.UpdateRequest(index,id).doc(Map.of(field,fieldValue)).upsert();
    }

    private <T> UpdateRequest<?,?> getUpdateSingleField(String index,String id,String field,T fieldValue){
        return new UpdateRequest.Builder<>().index(index).id(id).doc(Map.of(field, fieldValue)).build();
    }
}
