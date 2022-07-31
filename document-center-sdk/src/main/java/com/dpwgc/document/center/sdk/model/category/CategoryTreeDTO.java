package com.dpwgc.document.center.sdk.model.category;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CategoryTreeDTO {

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
     * 分类推荐分值（可按此字段对分类进行排序）
     */
    private Long score;

    /**
     * 分类创建时间
     */
    private Long createTime;

    /**
     * 分类更新时间
     */
    private Long updateTime;

    /**
     * 子类别对象
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CategoryTreeDTO> children;
}
