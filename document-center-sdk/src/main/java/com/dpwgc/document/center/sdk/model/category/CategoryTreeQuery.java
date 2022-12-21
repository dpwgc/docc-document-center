package com.dpwgc.document.center.sdk.model.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "完整分类树查询-接口参数")
public class CategoryTreeQuery {
    @ApiModelProperty(value = "应用id",required = true)
    private String appId;
    @ApiModelProperty(value = "是否显示分类详情")
    private Boolean showDetail;
}
