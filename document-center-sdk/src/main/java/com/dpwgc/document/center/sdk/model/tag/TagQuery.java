package com.dpwgc.document.center.sdk.model.tag;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel("文档标签查询-接口参数")
public class TagQuery {

    @ApiModelProperty(value = "应用id")
    private String appId;

    @ApiModelProperty(value = "标签更新时间区间的起始位置")
    private Long startUpdateTime;

    @ApiModelProperty(value = "标签更新时间区间的结束位置")
    private Long endUpdateTime;

    @ApiModelProperty(value = "分页起始")
    private Integer pageIndex;

    @ApiModelProperty(value = "分页大小")
    private Integer pageSize;
}
