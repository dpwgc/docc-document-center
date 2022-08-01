package com.dpwgc.document.center.sdk.model.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "删除文档分类-接口参数")
@Getter
@Setter
public class DeleteCategoryCommand {

    /**
     * 分类所属应用id
     */
    @ApiModelProperty(value = "分类所属应用id")
    private String appId;

    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类id")
    private String categoryId;
}
