package com.dpwgc.document.center.sdk.model.tag;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "删除（隐藏）文档标签-接口参数")
@Getter
@Setter
public class DeleteTagCommand {

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
     * 标签状态（0-隐藏，1-正常）
     */
    @ApiModelProperty(value = "标签状态（0-隐藏，1-正常）")
    private Integer status;
}
