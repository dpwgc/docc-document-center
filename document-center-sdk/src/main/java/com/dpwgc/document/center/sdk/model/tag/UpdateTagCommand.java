package com.dpwgc.document.center.sdk.model.tag;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@ApiModel(value = "更新文档标签旗下的文档数量-接口参数")
@Getter
@Setter
public class UpdateTagCommand {

    /**
     * 标签所属应用id
     */
    @ApiModelProperty(value = "标签所属应用id", required = true)
    @NotEmpty(message = "appId is empty")
    private String appId;

    /**
     * 标签名称
     */
    @ApiModelProperty(value = "标签名称（等价于该应用内的标签唯一标识）", required = true)
    @NotEmpty(message = "tagName is empty")
    private String tagName;

    /**
     * 带有该标签的文档数量
     */
    @ApiModelProperty(value = "带有该标签的文档数量")
    private Long number;

    /**
     * 标签状态（0-隐藏，1-正常）
     */
    @ApiModelProperty(value = "标签状态（0-隐藏，1-正常）")
    private Integer status;
}
