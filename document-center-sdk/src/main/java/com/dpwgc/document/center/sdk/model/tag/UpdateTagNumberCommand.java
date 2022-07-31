package com.dpwgc.document.center.sdk.model.tag;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTagNumberCommand {

    /**
     * 所属应用id
     */
    private String appId;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 带有该标签的文档数量
     */
    private Long number;
}
