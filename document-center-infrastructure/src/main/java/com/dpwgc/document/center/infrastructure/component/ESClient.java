package com.dpwgc.document.center.infrastructure.component;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.dpwgc.document.center.infrastructure.dal.document.entity.DocumentPO;
import com.dpwgc.document.center.infrastructure.util.JsonUtil;
import com.dpwgc.document.center.infrastructure.util.LogUtil;
import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.base.Status;
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
     *
     * @param indexName 索引名称
     * @return Boolean
     */
    public Boolean existsIndex(String indexName) {
        try {
            BooleanResponse booleanResponse = client.indices().exists(exists -> exists.index(indexName));
            return booleanResponse.value();
        } catch (Exception e) {
            LogUtil.error("es exists index error: " + e);
            return false;
        }
    }

    /**
     * 创建索引
     *
     * @param indexName 索引名称
     * @return Boolean
     */
    public Boolean createIndex(String indexName) {
        try {
            CreateIndexResponse indexResponse = client.indices().create(create -> create.index(indexName));
            LogUtil.info("create index: " + indexResponse.index());
            return true;
        } catch (Exception e) {
            LogUtil.error("es create index error: " + e);
            return false;
        }
    }

    /**
     * 插入文档
     *
     * @param indexName  索引名称
     * @param documentPO 文档对象
     * @return 主键id
     */
    public String insertDocument(String indexName, DocumentPO documentPO) {

        try {
            //DocumentPO转Json字符串
            String documentJson = JsonUtil.toJson(documentPO);

            //写入ES
            IndexResponse indexResponse = client.index(index -> index
                    .index(indexName)
                    .document(JsonUtil.fromJson(documentJson, Map.class)));

            return indexResponse.id();
        } catch (Exception e) {
            LogUtil.error("es insert document error: " + e);
            return null;
        }
    }

    /**
     * 更新文档
     *
     * @param indexName  索引名称
     * @param id         主键id
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
                            .doc(JsonUtil.fromJson(documentJson, Map.class))
                    , Map.class);
            return true;
        } catch (Exception e) {
            LogUtil.error("es update document error: " + e);
            return false;
        }
    }

    /**
     * 删除文档
     *
     * @param indexName 索引名称
     * @param id        主键id
     * @return Boolean
     */
    public Boolean deleteDocument(String indexName, String id) {

        try {
            client.delete(delete -> delete
                    .index(indexName)
                    .id(id)
            );
            return true;
        } catch (Exception e) {
            LogUtil.error("es delete document error: " + e);
            return false;
        }
    }

    // ============ 检索操作 ============

    /**
     * 根据ES主键id检索文档
     *
     * @param indexName 索引名称
     * @param id        主键id
     * @return List<Hit < Object>>
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
                                                    .query(Status.NORMAL)
                                            )
                                    )
                            )
                    )
                    //排序（例：sortField: update_time 。sortOrder: SortOrder.Desc/SortOrder.Asc）
                    .sort(sort -> sort.field(field -> field.field("id").order(SortOrder.Desc))), Object.class
            );
            return search.hits().hits();
        } catch (Exception e) {
            LogUtil.error("es search document by id error: " + e);
            return null;
        }
    }

    /**
     * 文档检索
     *
     * @param indexName           索引名称
     * @param categoryId          分类id
     * @param authorId            作者id
     * @param type                类型
     * @param tag                 标签
     * @param keyword             关键字
     * @param documentQueryCommon 文档查询通用字段
     * @return PageBase<List < Hit < Object>>>
     */
    public PageBase<List<Hit<Object>>> searchDocument(String indexName, String categoryId, String authorId, Integer type, String tag, String keyword, DocumentQueryCommon documentQueryCommon) {
        //
        BoolQuery.Builder bool = new BoolQuery.Builder();
        bool.must(m -> m
                .match(match -> match
                        .field("app_id")
                        .query(documentQueryCommon.getAppId())
                )
        );
        if (isEnable(categoryId)) {
            bool.must(must -> must
                    .match(match -> match
                            .field("category_id")
                            .query(categoryId)
                    )
            );
        }
        if (isEnable(authorId)) {
            bool.must(must -> must
                    .match(match -> match
                            .field("author_id")
                            .query(authorId)
                    )
            );
        }
        if (isEnable(type)) {
            bool.must(must -> must
                    .match(match -> match
                            .field("type")
                            .query(type)
                    )
            );
        }
        if (isEnable(tag)) {
            bool.must(must -> must
                    .fuzzy(fuzzy -> fuzzy
                            .field("tags")
                            .value(tag)
                            .fuzziness("0")
                    )
            );
        }
        if (isEnable(keyword)) {
            bool.should(should -> should
                    .fuzzy(fuzzy -> fuzzy
                            .field("title")
                            .value(keyword)
                            .fuzziness("0")
                    )
            );
            bool.should(should -> should
                    .fuzzy(fuzzy -> fuzzy
                            .field("content")
                            .value(keyword)
                            .fuzziness("0")
                    )
            );
            bool.should(should -> should
                    .fuzzy(fuzzy -> fuzzy
                            .field("tags")
                            .value(keyword)
                            .fuzziness("0")
                    )
            );
            bool.should(should -> should
                    .fuzzy(fuzzy -> fuzzy
                            .field("summary")
                            .value(keyword)
                            .fuzziness("0")
                    )
            );
        }
        bool.must(must -> must
                .range(range -> range
                        //返回的文档权限等级要<=用户权限等级
                        .field("auth_level")
                        .lte(JsonData.of(documentQueryCommon.getAuthLevel()))
                )
        );
        bool.must(must -> must
                .match(match -> match
                        .field("status")
                        .query(Status.NORMAL)
                )
        );
        bool.must(must -> must
                .range(range -> range
                        .field("update_time")
                        .gte(JsonData.of(documentQueryCommon.getStartUpdateTime()))
                        .lt(JsonData.of(documentQueryCommon.getEndUpdateTime()))
                )
        );
        try {
            // 检索分类id(精准查询)
            SearchResponse<Object> search = client.search(s -> s
                    .index(indexName)
                    .query(query -> query
                            .bool(bool.build())
                    )
                    //过滤，不返回该字段信息
                    .source(source -> source
                            .filter(filter -> filter
                                    .excludes("content")
                            )
                    )
                    //聚合统计-该分类旗下的文档收藏总数、点赞总数、阅读量总数
                    .aggregations("loveTotal", aggregations -> aggregations.sum(sum -> sum.field("love")))
                    .aggregations("likeTotal", aggregations -> aggregations.sum(sum -> sum.field("like")))
                    .aggregations("readTotal", aggregations -> aggregations.sum(sum -> sum.field("read")))
                    //分页查询
                    .from(documentQueryCommon.getPageIndex())
                    .size(documentQueryCommon.getPageSize())
                    //排序（例：sortField: update_time 。sortOrder: SortOrder.Desc/SortOrder.Asc）
                    .sort(sort -> sort.field(field -> field.field(documentQueryCommon.getSortField()).order(documentQueryCommon.getSortOrder()))), Object.class
            );
            return PageBase.getPageBase(search.hits().total().value(), search.hits().hits());
        } catch (Exception e) {
            LogUtil.error("es search document error: " + e);
            return null;
        }
    }

    /**
     * 文档数据聚合统计
     *
     * @param indexName           索引名称
     * @param appId               应用id
     * @param categoryId          分类id
     * @param authorId            作者id
     * @param type                类型
     * @param tag                 标签
     * @return PageBase<List < Hit < Object>>>
     */
    public PageBase<List<Hit<Object>>> aggregationsDocument(String indexName, String appId, String categoryId, String authorId, Integer type, String tag) {
        //
        BoolQuery.Builder bool = new BoolQuery.Builder();
        bool.must(m -> m
                .match(match -> match
                        .field("app_id")
                        .query(appId)
                )
        );
        if (isEnable(categoryId)) {
            bool.must(must -> must
                    .match(match -> match
                            .field("category_id")
                            .query(categoryId)
                    )
            );
        }
        if (isEnable(authorId)) {
            bool.must(must -> must
                    .match(match -> match
                            .field("author_id")
                            .query(authorId)
                    )
            );
        }
        if (isEnable(type)) {
            bool.must(must -> must
                    .match(match -> match
                            .field("type")
                            .query(type)
                    )
            );
        }
        if (isEnable(tag)) {
            bool.must(must -> must
                    .fuzzy(fuzzy -> fuzzy
                            .field("tags")
                            .value(tag)
                            .fuzziness("0")
                    )
            );
        }
        bool.must(must -> must
                .match(match -> match
                        .field("status")
                        .query(Status.NORMAL)
                )
        );
        try {
            // 检索分类id(精准查询)
            SearchResponse<Object> search = client.search(s -> s
                    .index(indexName)
                    .query(query -> query
                            .bool(bool.build())
                    )
                    //过滤，只返回这些字段信息
                    .source(source -> source
                            .filter(filter -> filter
                                    .includes("loveTotal","likeTotal","readTotal")
                            )
                    )
                    //聚合统计-该分类旗下的文档收藏总数、点赞总数、阅读量总数
                    .aggregations("loveTotal", aggregations -> aggregations.sum(sum -> sum.field("love")))
                    .aggregations("likeTotal", aggregations -> aggregations.sum(sum -> sum.field("like")))
                    .aggregations("readTotal", aggregations -> aggregations.sum(sum -> sum.field("read")))
                    //分页查询
                    .from(0)
                    .size(0)
                    , Object.class
            );
            return PageBase.getPageBase(search.hits().total().value(), search.hits().hits());
        } catch (Exception e) {
            LogUtil.error("es aggregations document error: " + e);
            return null;
        }
    }

    private boolean isEnable(String field) {
        return field != null && field.length() > 0;
    }

    private boolean isEnable(Integer field) {
        return field != null && field != 0;
    }
}
