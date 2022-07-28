package com.dpwgc.document.center.domain.category;

import lombok.Getter;
import lombok.Setter;

/**
 * 文档分类
 */
@Setter
@Getter
public class Category {

    /**
     * 主键id
     */
    private String id;

    /**
     * 所属应用id
     */
    private String appId;

    /**
     * 分类id
     */
    private String categoryId;

    /**
     * 父类id（如果是空字符串则表示这是顶级类别）
     */
    private String parentId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类概要
     */
    private String summary;

    /**
     * 分类推荐分值（可按此字段对分类进行排序）
     */
    private Long score;

    /**
     * 分类状态（0-删除，1-正常）
     */
    private Integer status;

    /**
     * 分类创建时间
     */
    private Long createTime;

    /**
     * 分类更新时间
     */
    private Long updateTime;

    protected Category create(String appId, String categoryId, String parentId, String categoryName, String summary, Long score, Long createTime, Long updateTime) {

        this.status = 1;

        this.appId = appId;
        this.categoryId = categoryId;
        this.parentId = parentId;
        this.categoryName = categoryName;
        this.summary = summary;
        this.score = score;

        this.createTime = createTime;
        this.updateTime = updateTime;

        return this;
    }
}
