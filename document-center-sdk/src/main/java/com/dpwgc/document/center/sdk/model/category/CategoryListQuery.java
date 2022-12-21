package com.dpwgc.document.center.sdk.model.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "分类列表查询-接口参数")
public class CategoryListQuery {
    @ApiModelProperty(value = "应用id",required = true)
    private String appId;
    @ApiModelProperty(value = "父类id（传空字符串返回最顶层类别列表）",required = true)
    private String parentId;
    @ApiModelProperty(value = "是否显示分类详情")
    private Boolean showDetail;
}
