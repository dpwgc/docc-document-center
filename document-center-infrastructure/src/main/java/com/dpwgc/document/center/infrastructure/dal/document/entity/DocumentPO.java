package com.dpwgc.document.center.infrastructure.dal.document.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DocumentPO {

    /**
     * ES主键id
     */
    @JsonProperty("id")
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
    private String authorId;

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
    @JsonProperty("tags")
    private String tags;

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
    @JsonProperty("love")
    private Long love;

    /**
     * 文档点赞数（可按此字段对文档进行排序）
     */
    @JsonProperty("like")
    private Long like;

    /**
     * 文档阅读数（可按此字段对文档进行排序）
     */
    @JsonProperty("read")
    private Long read;

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
