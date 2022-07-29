package com.dpwgc.document.center.sdk.model.document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDocumentScoreCommand {

    /**
     * ES主键id
     */
    private String id;

    /**
     * 文档推荐分值（可按此字段对文档进行排序，用于热门文档推荐）
     */
    private Long score;
}
