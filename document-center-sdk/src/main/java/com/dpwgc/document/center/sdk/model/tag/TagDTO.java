package com.dpwgc.document.center.sdk.model.tag;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "文档标签信息-DTO")
@Setter
@Getter
public class TagDTO {

    /**
     * 标签名称
     */
    @ApiModelProperty(value = "标签名称")
    private String tagName;

    /**
     * 带有该标签的文档数量
     */
    @ApiModelProperty(value = "带有该标签的文档数量")
    private Long number;

    /**
     * 标签创建时间
     */
    @ApiModelProperty(value = "标签创建时间")
    private Long createTime;

    /**
     * 标签更新时间
     */
    @ApiModelProperty(value = "标签更新时间")
    private Long updateTime;
}
