package com.dpwgc.document.center.infrastructure.component;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.dpwgc.document.center.infrastructure.dal.document.entity.DocumentPO;
import com.dpwgc.document.center.infrastructure.util.FieldUtil;
import com.dpwgc.document.center.infrastructure.util.JsonUtil;
import com.dpwgc.document.center.infrastructure.util.LogUtil;
import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.common.DocumentQueryCommon;
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
            LogUtil.info("create index: "+indexResponse.index());
            return true;
        } catch (Exception e) {
            LogUtil.error("es create index error: "+e);
            return false;
        }
    }

    /**
     * 插入文档
     * @param indexName 索引名称
     * @param documentPO 文档对象
     * @return 主键id
     */
    public String insertDocument(String indexName,DocumentPO documentPO) {

        try {
            //DocumentPO转Json字符串
            String documentJson = JsonUtil.toJson(documentPO);

            //写入ES
            IndexResponse indexResponse = client.index(index -> index
                    .index(indexName)
                    .document(JsonUtil.fromJson(documentJson,Map.class)));

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
     * @param documentPO 文档对象
     * @return Boolean
     */
    public Boolean updateDocument(String indexName, String id, DocumentPO documentPO) {

        try {
            //DocumentPO转Json字符串
            String documentJson = JsonUtil.toJson(documentPO);

            client.update(update -> update
                            .index(indexName)
                            .id(id)
                            .doc(JsonUtil.fromJson(documentJson,Map.class))
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
     * 根据ES主键id检索文档
     * @param indexName 索引名称
     * @param id 主键id
     * @return List<Hit<Object>>
     */
    public List<Hit<Object>> searchDocumentById(String indexName, String id) {

        try {
            SearchResponse<Object> search = client.search(s -> s
                    .index(indexName)
                    .query(query -> query
                            .bool(bool -> bool
                                    .must(must -> must
                                            .match(match -> match
                                                    .field("id")
                                                    .query(id)
                                            )
                                    )
                                    .must(must -> must
                                            .match(match -> match
                                                    .field("status")
                                                    .query(1)
                                            )
                                    )
                            )
                    )
                    //排序（例：sortField: update_time 。sortOrder: SortOrder.Desc/SortOrder.Asc）
                    .sort(sort -> sort.field(field -> field.field("id").order(SortOrder.Desc))),Object.class
            );
            return search.hits().hits();
        } catch (Exception e) {
            LogUtil.error("es search document by id error: "+e);
            return null;
        }
    }

    /**
     * 返回应用内的文档列表
     * @param indexName 索引名称
     * @return List<Hit<Object>>
     */
    public PageBase<List<Hit<Object>>> searchDocument(String indexName, DocumentQueryCommon documentQueryCommon) {

        try {
            SearchResponse<Object> search = client.search(s -> s
                    .index(indexName)
                    //按关键词检索文档标题、内容、标签、摘要(模糊查询，不允许错字)
                    .query(query -> query
                            .bool(bool -> bool
                                    .must(must -> must
                                            .match(match -> match
                                                    .field("app_id")
                                                    .query(documentQueryCommon.getAppId())
                                            )
                                    )
                                    .must(must -> must
                                            .range(range -> range
                                                    //返回的文档权限等级要<=用户权限等级
                                                    .field("auth_level")
                                                    .lte(JsonData.of(documentQueryCommon.getAuthLevel()))
                                            )
                                    )
                                    .must(must -> must
                                            .match(match -> match
                                                    .field("status")
                                                    .query(1)
                                            )
                                    )
                            )
                    )
                    //分页查询
                    .from(documentQueryCommon.getPageIndex())
                    .size(documentQueryCommon.getPageSize())
                    //排序（例：sortField: update_time 。sortOrder: SortOrder.Desc/SortOrder.Asc）
                    .sort(sort -> sort.field(field -> field.field(documentQueryCommon.getSortField()).order(documentQueryCommon.getSortOrder()))),Object.class
            );
            return PageBase.getPageBase(search.hits().total().value(),search.hits().hits());
        } catch (Exception e) {
            LogUtil.error("es search document by keyword error: "+e);
            return null;
        }
    }

    /**
     * 根据关键词检索应用内的所有文档
     * @param indexName 索引名称
     * @param keyword 关键词
     * @return List<Hit<Object>>
     */
    public PageBase<List<Hit<Object>>> searchDocumentByKeyword(String indexName, String keyword, DocumentQueryCommon documentQueryCommon) {

        try {
            SearchResponse<Object> search = client.search(s -> s
                    .index(indexName)
                    //按关键词检索文档标题、内容、标签、摘要(模糊查询，不允许错字)
                    .query(query -> query
                            .bool(bool -> bool
                                    .must(must -> must
                                            .match(match -> match
                                                    .field("app_id")
                                                    .query(documentQueryCommon.getAppId())
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
                                                    .lte(JsonData.of(documentQueryCommon.getAuthLevel()))
                                            )
                                    )
                                    .must(must -> must
                                            .match(match -> match
                                                    .field("status")
                                                    .query(1)
                                            )
                                    )
                            )
                    )
                    //分页查询
                    .from(documentQueryCommon.getPageIndex())
                    .size(documentQueryCommon.getPageSize())
                    //排序（例：sortField: update_time 。sortOrder: SortOrder.Desc/SortOrder.Asc）
                    .sort(sort -> sort.field(field -> field.field(documentQueryCommon.getSortField()).order(documentQueryCommon.getSortOrder()))),Object.class
            );
            return PageBase.getPageBase(search.hits().total().value(),search.hits().hits());
        } catch (Exception e) {
            LogUtil.error("es search document by keyword error: "+e);
            return null;
        }
    }

    /**
     * 根据分类id检索文档
     * @param indexName 索引名称
     * @param categoryId 分类id
     * @return List<Hit<Object>>
     */
    public PageBase<List<Hit<Object>>> searchDocumentByCategoryId(String indexName, String categoryId, DocumentQueryCommon documentQueryCommon) {

        try {
            // 检索分类id(精准查询)
            SearchResponse<Object> search = client.search(s -> s
                    .index(indexName)
                    .query(query -> query
                            .bool(bool -> bool
                                    .must(must -> must
                                            .match(match -> match
                                                    .field("app_id")
                                                    .query(documentQueryCommon.getAppId())
                                            )
                                    )
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
                                                    .lte(JsonData.of(documentQueryCommon.getAuthLevel()))
                                            )
                                    )
                                    .must(must -> must
                                            .match(match -> match
                                                    .field("status")
                                                    .query(1)
                                            )
                                    )
                            )
                    )
                    //分页查询
                    .from(documentQueryCommon.getPageIndex())
                    .size(documentQueryCommon.getPageSize())
                    //排序（例：sortField: update_time 。sortOrder: SortOrder.Desc/SortOrder.Asc）
                    .sort(sort -> sort.field(field -> field.field(documentQueryCommon.getSortField()).order(documentQueryCommon.getSortOrder()))),Object.class
            );
            return PageBase.getPageBase(search.hits().total().value(),search.hits().hits());
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
     * @return List<Hit<Object>>
     */
    public PageBase<List<Hit<Object>>> searchDocumentByCategoryIdAndKeyword(String indexName, String categoryId, String keyword, DocumentQueryCommon documentQueryCommon) {

        try {
            // 检索分类id(精准查询)
            SearchResponse<Object> search = client.search(s -> s
                    .index(indexName)
                    .query(query -> query
                            .bool(bool -> bool
                                    .must(must -> must
                                            .match(match -> match
                                                    .field("app_id")
                                                    .query(documentQueryCommon.getAppId())
                                            )
                                    )
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
                                                    .lte(JsonData.of(documentQueryCommon.getAuthLevel()))
                                            )
                                    )
                                    .must(must -> must
                                            .match(match -> match
                                                    .field("status")
                                                    .query(1)
                                            )
                                    )
                            )
                    )
                    //分页查询
                    .from(documentQueryCommon.getPageIndex())
                    .size(documentQueryCommon.getPageSize())
                    //排序（例：sortField: update_time 。sortOrder: SortOrder.Desc/SortOrder.Asc）
                    .sort(sort -> sort.field(field -> field.field(documentQueryCommon.getSortField()).order(documentQueryCommon.getSortOrder()))),Object.class
            );
            return PageBase.getPageBase(search.hits().total().value(),search.hits().hits());
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
     * @return List<Hit<Object>>
     */
    public PageBase<List<Hit<Object>>> searchDocumentByCategoryIdAndType(String indexName, String categoryId, Integer type, DocumentQueryCommon documentQueryCommon) {

        try {
            // 检索分类id(精准查询)
            SearchResponse<Object> search = client.search(s -> s
                    .index(indexName)
                    .query(query -> query
                            .bool(bool -> bool
                                    .must(must -> must
                                            .match(match -> match
                                                    .field("app_id")
                                                    .query(documentQueryCommon.getAppId())
                                            )
                                    )
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
                                                    .lte(JsonData.of(documentQueryCommon.getAuthLevel()))
                                            )
                                    )
                                    .must(must -> must
                                            .match(match -> match
                                                    .field("status")
                                                    .query(1)
                                            )
                                    )
                            )
                    )
                    //分页查询
                    .from(documentQueryCommon.getPageIndex())
                    .size(documentQueryCommon.getPageSize())
                    //排序（例：sortField: update_time 。sortOrder: SortOrder.Desc/SortOrder.Asc）
                    .sort(sort -> sort.field(field -> field.field(documentQueryCommon.getSortField()).order(documentQueryCommon.getSortOrder()))),Object.class
            );
            return PageBase.getPageBase(search.hits().total().value(),search.hits().hits());
        } catch (Exception e) {
            LogUtil.error("es search document by categoryId and type error: "+e);
            return null;
        }
    }

    /**
     * 根据作者id检索文档
     * @param indexName 索引名称
     * @param authorId 作者id
     * @return List<Hit<Object>>
     */
    public PageBase<List<Hit<Object>>> searchDocumentByAuthorId(String indexName, String authorId, DocumentQueryCommon documentQueryCommon) {

        try {
            // 检索作者id(精准查询)
            SearchResponse<Object> search = client.search(s -> s
                    .index(indexName)
                    .query(query -> query
                            .bool(bool -> bool
                                    .must(must -> must
                                            .match(match -> match
                                                    .field("app_id")
                                                    .query(documentQueryCommon.getAppId())
                                            )
                                    )
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
                                                    .lte(JsonData.of(documentQueryCommon.getAuthLevel()))
                                            )
                                    )
                                    .must(must -> must
                                            .match(match -> match
                                                    .field("status")
                                                    .query(1)
                                            )
                                    )
                            )
                    )
                    //分页查询
                    .from(documentQueryCommon.getPageIndex())
                    .size(documentQueryCommon.getPageSize())
                    //排序（例：sortField: update_time 。sortOrder: SortOrder.Desc/SortOrder.Asc）
                    .sort(sort -> sort.field(field -> field.field(documentQueryCommon.getSortField()).order(documentQueryCommon.getSortOrder()))),Object.class
            );
            return PageBase.getPageBase(search.hits().total().value(),search.hits().hits());
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
     * @return List<Hit<Object>>
     */
    public PageBase<List<Hit<Object>>> searchDocumentByAuthorIdAndKeyword(String indexName, String authorId, String keyword, DocumentQueryCommon documentQueryCommon) {

        try {
            // 检索分类id(精准查询)
            SearchResponse<Object> search = client.search(s -> s
                    .index(indexName)
                    .query(query -> query
                            .bool(bool -> bool
                                    .must(must -> must
                                            .match(match -> match
                                                    .field("app_id")
                                                    .query(documentQueryCommon.getAppId())
                                            )
                                    )
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
                                                    .lte(JsonData.of(documentQueryCommon.getAuthLevel()))
                                            )
                                    )
                                    .must(must -> must
                                            .match(match -> match
                                                    .field("status")
                                                    .query(1)
                                            )
                                    )
                            )
                    )
                    //分页查询
                    .from(documentQueryCommon.getPageIndex())
                    .size(documentQueryCommon.getPageSize())
                    //排序（例：sortField: update_time 。sortOrder: SortOrder.Desc/SortOrder.Asc）
                    .sort(sort -> sort.field(field -> field.field(documentQueryCommon.getSortField()).order(documentQueryCommon.getSortOrder()))),Object.class
            );
            return PageBase.getPageBase(search.hits().total().value(),search.hits().hits());
        } catch (Exception e) {
            LogUtil.error("es search document by authorId and keyword error: "+e);
            return null;
        }
    }

    /**
     * 根据标签检索文档
     * @param indexName 索引名称
     * @param tags 标签
     * @return List<Hit<Object>>
     */
    public PageBase<List<Hit<Object>>> searchDocumentByTags(String indexName, String tags, DocumentQueryCommon documentQueryCommon) {

        try {
            // 检索标签 (模糊查询，不允许错字)
            SearchResponse<Object> search = client.search(s -> s
                    .index(indexName)
                    .query(query -> query
                            .bool(bool -> bool
                                    .must(must -> must
                                            .match(match -> match
                                                    .field("app_id")
                                                    .query(documentQueryCommon.getAppId())
                                            )
                                    )
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
                                                    .lte(JsonData.of(documentQueryCommon.getAuthLevel()))
                                            )
                                    )
                                    .must(must -> must
                                            .match(match -> match
                                                    .field("status")
                                                    .query(1)
                                            )
                                    )
                            )
                    )
                    //分页查询
                    .from(documentQueryCommon.getPageIndex())
                    .size(documentQueryCommon.getPageSize())
                    //排序（例：sortField: update_time 。sortOrder: SortOrder.Desc/SortOrder.Asc）
                    .sort(sort -> sort.field(field -> field.field(documentQueryCommon.getSortField()).order(documentQueryCommon.getSortOrder()))),Object.class
            );
            return PageBase.getPageBase(search.hits().total().value(),search.hits().hits());
        } catch (Exception e) {
            LogUtil.error("es search document by tags error: "+e);
            return null;
        }
    }
}
