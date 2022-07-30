package com.dpwgc.document.center.sdk.model.tag;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TagDTO {

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

    /**
     * 标签创建时间
     */
    private Long createTime;

    /**
     * 标签更新时间
     */
    private Long updateTime;
}
