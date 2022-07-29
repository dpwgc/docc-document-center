package com.dpwgc.document.center.sdk.model.document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDocumentReadCommand {

    /**
     * ES主键id
     */
    private String id;

    /**
     * 文档阅读数（可按此字段对文档进行排序）
     */
    private Long read;
}
