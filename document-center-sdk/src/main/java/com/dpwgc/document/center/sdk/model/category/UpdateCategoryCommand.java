package com.dpwgc.document.center.sdk.model.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "更新文档分类信息-接口参数")
@Getter
@Setter
public class UpdateCategoryCommand {

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
     * 分类推荐分值（自定义，生成分类树时将按此字段对分类进行排序）
     */
    @ApiModelProperty(value = "分类推荐分值（自定义，生成分类树时将按此字段对分类进行排序）")
    private Long score;
}
