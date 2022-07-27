package com.dpwgc.document.query.infrastructure.component;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.dpwgc.document.query.infrastructure.util.FieldUtil;
import com.dpwgc.document.query.infrastructure.util.LogUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class ESClient {

    @Resource
    private ElasticsearchClient client;

    // ============ 命令操作 ============

    /**
     * 判断索引是否存在
     * @param indexName 索引名称
     * @return Boolean
     */
    public Boolean existsIndex(String indexName) {
        try {
            BooleanResponse booleanResponse = client.indices().exists(exists -> exists.index(indexName));
            return booleanResponse.value();
        } catch (Exception e) {
            LogUtil.error("es exists index error: "+e);
            return false;
        }
    }

    /**
     * 创建索引
     * @param indexName 索引名称
     * @return Boolean
     */
    public Boolean createIndex(String indexName) {
        try {
            CreateIndexResponse indexResponse = client.indices().create(create -> create.index(indexName));
            return true;
        } catch (Exception e) {
            LogUtil.error("es create index error: "+e);
            return false;
        }
    }

    /**
     * 插入文档
     * @param indexName 索引名称
     * @param document 文档对象
     * @return 主键id
     */
    public String insertDocument(String indexName,Object document) {

        try {
            //驼峰转下划线
            Map<String,Object> documentMap = FieldUtil.object2Map(document);
            //写入ES
            IndexResponse indexResponse = client.index(index -> index
                    .index(indexName)
                    .document(documentMap));
            return indexResponse.id();
        } catch (Exception e) {
            LogUtil.error("es insert document error: "+e);
            return null;
        }
    }

    /**
     * 更新文档
     * @param indexName 索引名称
     * @param id 主键id
     * @param document 文档对象
     * @return Boolean
     */
    public Boolean updateDocument(String indexName,String id,Object document) {

        try {
            //驼峰转下划线
            Map<String,Object> documentMap = FieldUtil.object2Map(document);
            client.update(update -> update
                            .index(indexName)
                            .id(id)
                            .doc(documentMap)
                    , Map.class);
            return true;
        } catch (Exception e) {
            LogUtil.error("es update document error: "+e);
            return false;
        }
    }

    /**
     * 删除文档
     * @param indexName 索引名称
     * @param id 主键id
     * @return Boolean
     */
    public Boolean deleteDocument(String indexName,String id) {

        try {
            client.delete(delete -> delete
                    .index(indexName)
                    .id(id)
            );
            return true;
        } catch (Exception e) {
            LogUtil.error("es delete document error: "+e);
            return false;
        }
    }

    // ============ 检索操作 ============

    /**
     * 根据关键词检索文档
     * @param indexName 索引名称
     * @param keyword 关键词
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return List<Hit<Object>>
     */
    public List<Hit<Object>> searchDocumentByKeyword(String indexName, String keyword, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {

        try {
            SearchResponse<Object> search = client.search(s -> s
                    .index(indexName)
                    //按关键词检索文档标题、内容、标签、摘要(模糊查询，不允许错字)
                    .query(query -> query
                            .bool(bool -> bool
                                    .should(should -> should
                                            .fuzzy(fuzzy -> fuzzy
                                                    .field("title")
                                                    .value(keyword)
                                                    .fuzziness("0")
                                            )
                                    )
                                    .should(should -> should
                                            .fuzzy(fuzzy -> fuzzy
                                                    .field("content")
                                                    .value(keyword)
                                                    .fuzziness("0")
                                            )
                                    )
                                    .should(should -> should
                                            .fuzzy(fuzzy -> fuzzy
                                                    .field("tags")
                                                    .value(keyword)
                                                    .fuzziness("0")
                                            )
                                    )
                                    .should(should -> should
                                            .fuzzy(fuzzy -> fuzzy
                                                    .field("abstract")
                                                    .value(keyword)
                                                    .fuzziness("0")
                                            )
                                    )
                            )
                    )
                    //分页查询
                    .from(pageIndex)
                    .size(pageSize)
                    //排序（例：sortField: update_time 。sortOrder: SortOrder.Desc/SortOrder.Asc）
                    .sort(sort -> sort.field(field -> field.field(sortField).order(sortOrder))),Object.class
            );
            return search.hits().hits();
        } catch (Exception e) {
            LogUtil.error("es search document by keyword error: "+e);
            return null;
        }
    }

    /**
     * 根据分类id检索文档
     * @param indexName 索引名称
     * @param categoryId 分类id
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return List<Hit<Object>>
     */
    public List<Hit<Object>> searchDocumentByCategoryId(String indexName, String categoryId, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {

        try {
            // 检索分类id(精准查询)
            SearchResponse<Object> search = client.search(s -> s
                    .index(indexName)
                    .query(query -> query
                            .match(match -> match
                                    .field("category_id")
                                    .query(categoryId)
                            )
                    )
                    //分页查询
                    .from(pageIndex)
                    .size(pageSize)
                    //排序（例：sortField: update_time 。sortOrder: SortOrder.Desc/SortOrder.Asc）
                    .sort(sort -> sort.field(field -> field.field(sortField).order(sortOrder))),Object.class
            );
            return search.hits().hits();
        } catch (Exception e) {
            LogUtil.error("es search document by categoryId error: "+e);
            return null;
        }
    }

    /**
     * 根据标签检索文档
     * @param indexName 索引名称
     * @param tags 标签
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return List<Hit<Object>>
     */
    public List<Hit<Object>> searchDocumentByTags(String indexName, String tags, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {

        try {
            // 检索标签 (模糊查询，不允许错字)
            SearchResponse<Object> search = client.search(s -> s
                    .index(indexName)
                    .query(query -> query
                            .fuzzy(fuzzy -> fuzzy
                                    .field("tags")
                                    .value(tags)
                                    .fuzziness("0")
                            )
                    )
                    //分页查询
                    .from(pageIndex)
                    .size(pageSize)
                    //排序（例：sortField: update_time 。sortOrder: SortOrder.Desc/SortOrder.Asc）
                    .sort(sort -> sort.field(field -> field.field(sortField).order(sortOrder))),Object.class
            );
            return search.hits().hits();
        } catch (Exception e) {
            LogUtil.error("es search document by categoryId error: "+e);
            return null;
        }
    }
}
