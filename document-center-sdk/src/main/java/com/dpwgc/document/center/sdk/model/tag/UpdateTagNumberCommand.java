package com.dpwgc.document.center.sdk.model.tag;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "更新文档标签旗下的文档数量-接口参数")
@Getter
@Setter
public class UpdateTagNumberCommand {

    /**
     * 标签所属应用id
     */
    @ApiModelProperty(value = "标签所属应用id")
    private String appId;

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
}