package com.dpwgc.document.center.sdk.model.document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDocumentMainCommand {

    /**
     * ES主键id
     */
    private String id;

    /**
     * 分类id
     */
    private String categoryId;

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
}
