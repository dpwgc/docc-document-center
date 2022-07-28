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
}