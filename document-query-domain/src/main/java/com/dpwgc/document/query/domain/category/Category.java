package com.dpwgc.document.query.domain.category;

import com.dpwgc.document.query.domain.document.Document;
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

    protected Category create(String appId, String categoryId, String parentId, String categoryName) {

        this.createTime = System.currentTimeMillis();
        this.updateTime = System.currentTimeMillis();

        this.status = 1;

        this.appId = appId;
        this.categoryId = categoryId;
        this.parentId = parentId;
        this.categoryName = categoryName;

        return this;
    }
}
