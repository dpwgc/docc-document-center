package com.dpwgc.document.center.sdk.model.document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDocumentTypeCommand {

    /**
     * ES主键id
     */
    private String id;

    /**
     * 文档类型
     */
    private Integer type;
}
