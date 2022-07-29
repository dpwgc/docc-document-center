package com.dpwgc.document.center.sdk.model.document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDocumentLoveCommand {

    /**
     * ES主键id
     */
    private String id;

    /**
     * 文档收藏数（可按此字段对文档进行排序）
     */
    private Long love;
}
