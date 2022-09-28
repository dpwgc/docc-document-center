package com.dpwgc.document.center.infrastructure.dal.document.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Setter
@Getter
public class DocumentPO {

    /**
     * ES主键id
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String id;

    /**
     * 应用id
     */
    @JsonProperty("app_id")
    private String appId;

    /**
     * 分类id
     */
    @JsonProperty("category_id")
    private String categoryId;

    /**
     * 作者id
     */
    @JsonProperty("author_id")
    private Set<String> authorId;

    /**
     * 文档id
     */
    @JsonProperty("document_id")
    private String documentId;

    /**
     * 文档标题
     */
    @JsonProperty("title")
    private String title;

    /**
     * 文档内容
     */
    @JsonProperty("content")
    private String content;

    /**
     * 文档标签
     */
    @JsonProperty("tag")
    private Set<String> tag;

    /**
     * 文档备注
     */
    @JsonProperty("remarks")
    private String remarks;

    /**
     * 文档附加内容（自定义）
     */
    @JsonProperty("extra")
    private String extra;

    /**
     * 文档总结摘要
     */
    @JsonProperty("summary")
    private String summary;

    /**
     * 文档查看权限级别（0～99自定义，用户权限等级如果低于authLevel，则不能查看该文章）
     */
    @JsonProperty("auth_level")
    private Integer authLevel;

    /**
     * 文档推荐分值（可按此字段对文档进行排序，用于热门文档推荐）
     */
    @JsonProperty("score")
    private Long score;

    /**
     * 文档收藏数（可按此字段对文档进行排序）
     */
    @JsonProperty("love_num")
    private Long loveNum;

    /**
     * 文档点赞数（可按此字段对文档进行排序）
     */
    @JsonProperty("like_num")
    private Long likeNum;

    /**
     * 文档阅读数（可按此字段对文档进行排序）
     */
    @JsonProperty("read_num")
    private Long readNum;

    /**
     * 文档分享数（可按此字段对文档进行排序）
     */
    @JsonProperty("share_num")
    private Long shareNum;

    /**
     * 文档评论总数（可按此字段对文档进行排序）
     */
    @JsonProperty("comment_num")
    private Long commentNum;

    /**
     * 文档检索过滤条件（自定义，例：1-仅自己可见、2-所有人可见）
     */
    @JsonProperty("filter")
    private Set<Integer> filter;

    /**
     * 文档属性（自定义，例：1-转载文章、2-原创文章）
     */
    @JsonProperty("attr")
    private Set<Integer> attr;

    /**
     * 文档类型（自定义，例：0-普通文档，1-置顶文档）
     */
    @JsonProperty("type")
    private Integer type;

    /**
     * 文档状态（0-删除，1-正常）
     */
    @JsonProperty("status")
    private Integer status;

    /**
     * 文档创建时间
     */
    @JsonProperty("create_time")
    private Long createTime;

    /**
     * 文档更新时间
     */
    @JsonProperty("update_time")
    private Long updateTime;
}
