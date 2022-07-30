package com.dpwgc.document.center.domain.tag;

import lombok.Getter;
import lombok.Setter;

/**
 * 文档标签
 */
@Setter
@Getter
public class Tag {

    /**
     * 主键id
     */
    private String id;

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
     * 标签状态（0-隐藏，1-正常）
     */
    private Integer status;

    /**
     * 标签创建时间
     */
    private Long createTime;

    /**
     * 标签更新时间
     */
    private Long updateTime;

    protected Tag create(String appId, String tagName) {

        this.status = 1;

        this.appId = appId;
        this.tagName = tagName;
        this.number = 0L;

        this.createTime = System.currentTimeMillis();
        this.updateTime = System.currentTimeMillis();

        return this;
    }
}
