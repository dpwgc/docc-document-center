package com.dpwgc.document.center.sdk.model.document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDocumentAuthLevelCommand {

    /**
     * ES主键id
     */
    private String id;

    /**
     * 文档查看权限级别（0～99自定义，用户权限等级如果低于authLevel，则不能查看该文章）
     */
    private Integer authLevel;
}
