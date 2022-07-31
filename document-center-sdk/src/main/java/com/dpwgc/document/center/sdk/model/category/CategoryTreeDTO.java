package com.dpwgc.document.center.sdk.model.category;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel(value = "文档分类树-DTO")
@Setter
@Getter
public class CategoryTreeDTO {

    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类id")
    private String categoryId;

    /**
     * 父类id（如果是空字符串则表示这是顶级类别）
     */
    @ApiModelProperty(value = "父类id（如果是空字符串则表示这是顶级类别）")
    private String parentId;

    /**
     * 分类名称
     */
    @ApiModelProperty(value = "分类名称")
    private String categoryName;

    /**
     * 分类推荐分值（可按此字段对分类进行排序）
     */
    @ApiModelProperty(value = "分类推荐分值（自定义，生成分类树时将按此字段对分类进行排序）")
    private Long score;

    /**
     * 分类创建时间
     */
    @ApiModelProperty(value = "分类创建时间")
    private Long createTime;

    /**
     * 分类更新时间
     */
    @ApiModelProperty(value = "分类更新时间")
    private Long updateTime;

    /**
     * 子类别对象（该分类下的子类别列表）
     */
    @ApiModelProperty(value = "子类别对象（该分类下的子类别列表）")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CategoryTreeDTO> children;
}
