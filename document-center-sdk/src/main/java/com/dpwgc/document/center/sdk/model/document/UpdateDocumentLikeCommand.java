package com.dpwgc.document.center.sdk.model.document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDocumentLikeCommand {

    /**
     * ES主键id
     */
    private String id;

    /**
     * 文档点赞数（可按此字段对文档进行排序）
     */
    private Long like;
}
