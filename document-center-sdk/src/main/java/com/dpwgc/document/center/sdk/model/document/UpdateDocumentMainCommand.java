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
}
