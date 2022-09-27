package com.dpwgc.document.center.infrastructure.component;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.SourceFilter;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.dpwgc.document.center.infrastructure.dal.document.entity.DocumentPO;
import com.dpwgc.document.center.infrastructure.util.JsonUtil;
import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.base.Status;
import com.dpwgc.document.center.sdk.model.document.AggregationsQuery;
import com.dpwgc.document.center.sdk.model.document.DocumentQuery;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class ESClient {

    @Value("${optimistic.update.retry}")
    private int retry;

    @Resource
    private ElasticsearchClient client;

    // ============ 命令操作 ============

    /**
     * 判断索引是否存在
     *
     * @param indexName 索引名称
     * @return Boolean
     */
    public Boolean existsIndex(String indexName) throws IOException {
        BooleanResponse booleanResponse = client.indices().exists(exists -> exists.index(indexName));
        return booleanResponse.value();
    }

    /**
     * 创建索引
     *
     * @param indexName 索引名称
     * @return Boolean
     */
    public void createIndex(String indexName) throws IOException {
        client.indices().create(create -> create.index(indexName));
    }

    /**
     * 插入文档
     *
     * @param indexName  索引名称
     * @param documentPO 文档对象
     * @return 主键id
     */
    public String insertDocument(String indexName, DocumentPO documentPO) throws IOException {

        //DocumentPO转Json字符串
        String documentJson = JsonUtil.toJson(documentPO);

        //写入ES
        IndexResponse indexResponse = client.index(index -> index
                .index(indexName)
                .document(JsonUtil.fromJson(documentJson, Map.class)));

        return indexResponse.id();
    }

    /**
     * 更新文档
     *
     * @param indexName  索引名称
     * @param documentPO 文档对象
     * @return Boolean
     */
    public Boolean updateDocument(String indexName, DocumentPO documentPO) throws IOException {

        //DocumentPO转Json字符串
        String documentJson = JsonUtil.toJson(documentPO);

        UpdateResponse updateResponse = client.update(update -> update
                        .index(indexName)
                        .id(documentPO.getId())
                        // 乐观锁-更新失败重试次数
                        .retryOnConflict(retry)
                        // 更新文档
                        .doc(JsonUtil.fromJson(documentJson, Map.class))
                , Map.class);
        return updateResponse.id().length() > 0;
    }

    // ============ 检索操作 ============

    /**
     * 根据ES主键id检索文档
     *
     * @param indexName 索引名称
     * @param id        主键id
     * @return List<Hit < Object>>
     */
    public List<Hit<Object>> searchDocumentById(String indexName, String id) throws IOException {

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
                , Object.class
        );
        return search.hits().hits();
    }

    /**
     * 文档检索
     */
    public PageBase<List<Hit<Object>>> searchDocument(String indexName, DocumentQuery documentQuery) throws IOException {

        BoolQuery.Builder bool = new BoolQuery.Builder();
        if (isEnable(documentQuery.getAppId())) {
            bool.must(must -> must
                    .match(match -> match
                            .field("app_id")
                            .query(documentQuery.getAppId())
                    )
            );
        }
        if (isEnable(documentQuery.getCategoryId())) {
            bool.must(must -> must
                    .match(match -> match
                            .field("category_id")
                            .query(documentQuery.getCategoryId())
                    )
            );
        }
        if (isEnable(documentQuery.getAuthorId())) {
            bool.must(must -> must
                    .match(match -> match
                            .field("author_id")
                            .query(documentQuery.getAuthorId())
                    )
            );
        }
        if (isEnable(documentQuery.getType())) {
            bool.must(must -> must
                    .match(match -> match
                            .field("type")
                            .query(documentQuery.getType())
                    )
            );
        }
        if (isEnable(documentQuery.getFilter())) {
            bool.must(must -> must
                    .match(match -> match
                            .field("filter")
                            .query(documentQuery.getFilter())
                    )
            );
        }
        if (isEnable(documentQuery.getAttr())) {
            bool.must(must -> must
                    .match(match -> match
                            .field("attr")
                            .query(documentQuery.getAttr())
                    )
            );
        }
        if (isEnable(documentQuery.getTag())) {
            bool.must(must -> must
                    .fuzzy(fuzzy -> fuzzy
                            .field("tags")
                            .value(documentQuery.getTag())
                            .fuzziness("0")
                    )
            );
        }
        if (isEnable(documentQuery.getKeyword())) {
            bool.should(should -> should
                    .fuzzy(fuzzy -> fuzzy
                            .field("title")
                            .value(documentQuery.getKeyword())
                            .fuzziness("0")
                    )
            );
            bool.should(should -> should
                    .fuzzy(fuzzy -> fuzzy
                            .field("content")
                            .value(documentQuery.getKeyword())
                            .fuzziness("0")
                    )
            );
            bool.should(should -> should
                    .fuzzy(fuzzy -> fuzzy
                            .field("tags")
                            .value(documentQuery.getKeyword())
                            .fuzziness("0")
                    )
            );
            bool.should(should -> should
                    .fuzzy(fuzzy -> fuzzy
                            .field("summary")
                            .value(documentQuery.getKeyword())
                            .fuzziness("0")
                    )
            );
        }
        bool.must(must -> must
                .range(range -> range
                        //返回的文档权限等级要<=用户权限等级
                        .field("auth_level")
                        .lte(JsonData.of(documentQuery.getAuthLevel()))
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
                        .gte(JsonData.of(documentQuery.getStartUpdateTime()))
                        .lt(JsonData.of(documentQuery.getEndUpdateTime()))
                )
        );

        //是否显示文档主体内容
        SourceFilter.Builder filter = new SourceFilter.Builder();
        if (!documentQuery.getShowContent()) {
            filter.excludes("content");
        }

        //排序
        SortOrder sortOrder = null;
        if (documentQuery.getSortOrder().equals("desc")) {
            sortOrder = SortOrder.Desc;
        } else {
            sortOrder = SortOrder.Asc;
        }
        SortOrder finalSortOrder = sortOrder;

        // 检索分类id(精准查询)
        SearchResponse<Object> search = client.search(s -> s
                .index(indexName)
                .query(query -> query
                        .bool(bool.build())
                )
                .source(source -> source
                        .filter(filter.build())
                )
                //分页查询
                .from(documentQuery.getPageIndex())
                .size(documentQuery.getPageSize())
                //排序（例：sortField: update_time 。sortOrder: SortOrder.Desc/SortOrder.Asc）
                .sort(sort -> sort.field(field -> field.field(documentQuery.getSortField()).order(finalSortOrder))), Object.class
        );
        return PageBase.getPageBase(search.hits().total().value(), search.hits().hits());
    }

    /**
     * 文档数据聚合统计
     */
    public Map<String, Aggregate> aggregationsDocument(String indexName, AggregationsQuery aggregationsQuery) throws IOException {
        //
        BoolQuery.Builder bool = new BoolQuery.Builder();
        if (isEnable(aggregationsQuery.getAppId())) {
            bool.must(must -> must
                    .match(match -> match
                            .field("app_id")
                            .query(aggregationsQuery.getAppId())
                    )
            );
        }
        if (isEnable(aggregationsQuery.getCategoryId())) {
            bool.must(must -> must
                    .match(match -> match
                            .field("category_id")
                            .query(aggregationsQuery.getCategoryId())
                    )
            );
        }
        if (isEnable(aggregationsQuery.getAuthorId())) {
            bool.must(must -> must
                    .match(match -> match
                            .field("author_id")
                            .query(aggregationsQuery.getAuthorId())
                    )
            );
        }
        if (isEnable(aggregationsQuery.getType())) {
            bool.must(must -> must
                    .match(match -> match
                            .field("type")
                            .query(aggregationsQuery.getType())
                    )
            );
        }
        if (isEnable(aggregationsQuery.getTag())) {
            bool.must(must -> must
                    .fuzzy(fuzzy -> fuzzy
                            .field("tags")
                            .value(aggregationsQuery.getTag())
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

        // 检索分类id(精准查询)
        SearchResponse<Object> search = client.search(s -> s
                        .index(indexName)
                        .query(query -> query
                                .bool(bool.build())
                        )
                        //聚合统计-文档分享总数、收藏总数、点赞总数、阅读量总数、评论总数、正常文档总数
                        .aggregations("shareTotal", aggregations -> aggregations.sum(sum -> sum.field("share_num")))
                        .aggregations("loveTotal", aggregations -> aggregations.sum(sum -> sum.field("love_num")))
                        .aggregations("likeTotal", aggregations -> aggregations.sum(sum -> sum.field("like_num")))
                        .aggregations("readTotal", aggregations -> aggregations.sum(sum -> sum.field("read_num")))
                        .aggregations("commentTotal", aggregations -> aggregations.sum(sum -> sum.field("comment_num")))
                        .aggregations("documentTotal", aggregations -> aggregations.sum(sum -> sum.field("status")))
                        //分页查询
                        .from(0)
                        .size(0)
                , Object.class
        );
        return search.aggregations();
    }

    private boolean isEnable(String field) {
        return field != null && field.length() > 0;
    }

    private boolean isEnable(Integer field) {
        return field != null && field != 0;
    }
}
