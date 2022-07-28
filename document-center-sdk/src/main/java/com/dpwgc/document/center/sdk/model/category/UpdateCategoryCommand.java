package com.dpwgc.document.center.sdk.model.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCategoryCommand {

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
}
