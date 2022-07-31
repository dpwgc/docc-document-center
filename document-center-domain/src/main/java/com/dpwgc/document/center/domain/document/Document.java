package com.dpwgc.document.center.domain.document;

import lombok.Getter;
import lombok.Setter;

/**
 * 文档
 */
@Setter
@Getter
public class Document {

    /**
     * ES主键id
     */
    private String id;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 分类id
     */
    private String categoryId;

    /**
     * 作者id
     */
    private String authorId;

    /**
     * 文档id
     */
    private String documentId;

    /**
     * 文档标题
     */
    private String title;

    /**
     * 文档内容
     */
    private String content;

    /**
     * 文档标签
     */
    private String tags;

    /**
     * 文档总结摘要
     */
    private String summary;

    /**
     * 文档查看权限级别（0～99自定义，用户权限等级如果低于authLevel，则不能查看该文章）
     */
    private Integer authLevel;

    /**
     * 文档推荐分值（可按此字段对文档进行排序，用于热门文档推荐）
     */
    private Long score;

    /**
     * 文档收藏数（可按此字段对文档进行排序）
     */
    private Long love;

    /**
     * 文档点赞数（可按此字段对文档进行排序）
     */
    private Long like;

    /**
     * 文档阅读数（可按此字段对文档进行排序）
     */
    private Long read;

    /**
     * 文档类型（自定义，例：0-普通文档，1-置顶文档）
     */
    private Integer type;

    /**
     * 文档状态（0-删除，1-正常）
     */
    private Integer status;

    /**
     * 文档创建时间
     */
    private Long createTime;

    /**
     * 文档更新时间
     */
    private Long updateTime;

    protected Document create(String appId, String categoryId, String authorId, String documentId, String title, String content, String tags, String summary, Integer authLevel, Long score, Integer type) {

        this.status = 1;

        this.appId = appId;
        this.categoryId = categoryId;
        this.authorId = authorId;
        this.documentId = documentId;
        this.title = title;
        this.content = content;
        this.summary = summary;
        this.tags = tags;
        this.authLevel = authLevel;

        this.score = score;

        this.love = 0L;
        this.like = 0L;
        this.read = 0L;

        this.type = type;

        this.createTime = System.currentTimeMillis();
        this.updateTime = System.currentTimeMillis();

        return this;
    }
}
