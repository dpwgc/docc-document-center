package com.dpwgc.document.center.sdk.model.document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentDTO {

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
     * 文档类型（自定义，例：0-普通文档，1-置顶文档）
     */
    private Integer type;

    /**
     * 文档创建时间
     */
    private Long createTime;

    /**
     * 文档更新时间
     */
    private Long updateTime;
}