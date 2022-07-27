package com.dpwgc.document.query.infrastructure.dal.document.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DocumentPO {

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
}
