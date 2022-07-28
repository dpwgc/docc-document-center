package com.dpwgc.document.query.infrastructure.component;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.json.JsonData;
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
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return List<Hit<Object>>
     */
    public List<Hit<Object>> searchDocumentByKeyword(String indexName, String keyword, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {

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
                                                    .field("summary")
                                                    .value(keyword)
                                                    .fuzziness("0")
                                            )
                                    )
                                    .must(must -> must
                                            .range(range -> range
                                                    //返回的文档权限等级要<=用户权限等级
                                                    .field("auth_level")
                                                    .lte(JsonData.of(authLevel))
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
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return List<Hit<Object>>
     */
    public List<Hit<Object>> searchDocumentByCategoryId(String indexName, String categoryId, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {

        try {
            // 检索分类id(精准查询)
            SearchResponse<Object> search = client.search(s -> s
                    .index(indexName)
                    .query(query -> query
                            .bool(bool -> bool
                                    .must(must -> must
                                            .match(match -> match
                                                    .field("category_id")
                                                    .query(categoryId)
                                            )
                                    )
                                    .must(must -> must
                                            .range(range -> range
                                                    //返回的文档权限等级要<=用户权限等级
                                                    .field("auth_level")
                                                    .lte(JsonData.of(authLevel))
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
            LogUtil.error("es search document by categoryId error: "+e);
            return null;
        }
    }

    /**
     * 根据分类id与关键词检索文档
     * @param indexName 索引名称
     * @param categoryId 分类id
     * @param keyword 关键词
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return List<Hit<Object>>
     */
    public List<Hit<Object>> searchDocumentByCategoryIdAndKeyword(String indexName, String categoryId, String keyword, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {

        try {
            // 检索分类id(精准查询)
            SearchResponse<Object> search = client.search(s -> s
                    .index(indexName)
                    .query(query -> query
                            .bool(bool -> bool
                                    .must(must -> must
                                            .match(match -> match
                                                    .field("category_id")
                                                    .query(categoryId)
                                            )
                                    )
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
                                                    .field("summary")
                                                    .value(keyword)
                                                    .fuzziness("0")
                                            )
                                    )
                                    .must(must -> must
                                            .range(range -> range
                                                    //返回的文档权限等级要<=用户权限等级
                                                    .field("auth_level")
                                                    .lte(JsonData.of(authLevel))
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
            LogUtil.error("es search document by categoryId and keyword error: "+e);
            return null;
        }
    }

    /**
     * 根据分类id与文档类型type检索文档
     * @param indexName 索引名称
     * @param categoryId 分类id
     * @param type 文档类型
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return List<Hit<Object>>
     */
    public List<Hit<Object>> searchDocumentByCategoryIdAndType(String indexName, String categoryId, Integer type, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {

        try {
            // 检索分类id(精准查询)
            SearchResponse<Object> search = client.search(s -> s
                    .index(indexName)
                    .query(query -> query
                            .bool(bool -> bool
                                    .must(must -> must
                                            .match(match -> match
                                                    .field("category_id")
                                                    .query(categoryId)
                                            )
                                    )
                                    .must(must -> must
                                            .match(match -> match
                                                    .field("type")
                                                    .query(type)
                                            )
                                    )
                                    .must(must -> must
                                            .range(range -> range
                                                    //返回的文档权限等级要<=用户权限等级
                                                    .field("auth_level")
                                                    .lte(JsonData.of(authLevel))
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
            LogUtil.error("es search document by categoryId and type error: "+e);
            return null;
        }
    }

    /**
     * 根据作者id检索文档
     * @param indexName 索引名称
     * @param authorId 作者id
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return List<Hit<Object>>
     */
    public List<Hit<Object>> searchDocumentByAuthorId(String indexName, String authorId, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {

        try {
            // 检索作者id(精准查询)
            SearchResponse<Object> search = client.search(s -> s
                    .index(indexName)
                    .query(query -> query
                            .bool(bool -> bool
                                    .must(must -> must
                                            .match(match -> match
                                                    .field("author_id")
                                                    .query(authorId)
                                            )
                                    )
                                    .must(must -> must
                                            .range(range -> range
                                                    //返回的文档权限等级要<=用户权限等级
                                                    .field("auth_level")
                                                    .lte(JsonData.of(authLevel))
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
            LogUtil.error("es search document by authorId error: "+e);
            return null;
        }
    }

    /**
     * 根据作者id与关键词检索文档
     * @param indexName 索引名称
     * @param authorId 作者id
     * @param keyword 关键词
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return List<Hit<Object>>
     */
    public List<Hit<Object>> searchDocumentByAuthorIdAndKeyword(String indexName, String authorId, String keyword, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {

        try {
            // 检索分类id(精准查询)
            SearchResponse<Object> search = client.search(s -> s
                    .index(indexName)
                    .query(query -> query
                            .bool(bool -> bool
                                    .must(must -> must
                                            .match(match -> match
                                                    .field("author_id")
                                                    .query(authorId)
                                            )
                                    )
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
                                                    .field("summary")
                                                    .value(keyword)
                                                    .fuzziness("0")
                                            )
                                    )
                                    .must(must -> must
                                            .range(range -> range
                                                    //返回的文档权限等级要<=用户权限等级
                                                    .field("auth_level")
                                                    .lte(JsonData.of(authLevel))
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
            LogUtil.error("es search document by authorId and keyword error: "+e);
            return null;
        }
    }

    /**
     * 根据标签检索文档
     * @param indexName 索引名称
     * @param tags 标签
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return List<Hit<Object>>
     */
    public List<Hit<Object>> searchDocumentByTags(String indexName, String tags, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {

        try {
            // 检索标签 (模糊查询，不允许错字)
            SearchResponse<Object> search = client.search(s -> s
                    .index(indexName)
                    .query(query -> query
                            .bool(bool -> bool
                                    .must(must -> must
                                            .fuzzy(fuzzy -> fuzzy
                                                    .field("tags")
                                                    .value(tags)
                                                    .fuzziness("0")
                                            )
                                    )
                                    .must(must -> must
                                            .range(range -> range
                                                    //返回的文档权限等级要<=用户权限等级
                                                    .field("auth_level")
                                                    .lte(JsonData.of(authLevel))
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
            LogUtil.error("es search document by tags error: "+e);
            return null;
        }
    }
}
