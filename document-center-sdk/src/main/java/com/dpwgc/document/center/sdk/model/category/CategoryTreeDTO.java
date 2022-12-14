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
     * 分类详情
     */
    @ApiModelProperty(value = "分类详情")
    private String detail;

    /**
     * 分类附加内容（自定义）
     */
    @ApiModelProperty(value = "分类附加内容（自定义）")
    private String extra;

    /**
     * 分类推荐分值（自由分配，返回分类列表时将按此字段对分类进行排序）
     */
    @ApiModelProperty(value = "分类推荐分值（自由分配，返回分类列表时将按此字段对分类进行排序）")
    private Long score;

    /**
     * 分类属性（自定义，例：1-热门分类、0-普通分类）
     */
    @ApiModelProperty(value = "分类属性（自定义，例：1-热门分类、0-普通分类）")
    private Integer attr;

    /**
     * 分类类型（自定义，例：1-官方分类，0-普通分类）
     */
    @ApiModelProperty(value = "分类类型（自定义，例：1-官方分类，0-普通分类）")
    private Integer type;

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
