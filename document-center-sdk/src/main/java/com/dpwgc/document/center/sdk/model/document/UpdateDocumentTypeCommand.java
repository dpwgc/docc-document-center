package com.dpwgc.document.center.sdk.model.document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "更新文档类型-接口参数")
@Getter
@Setter
public class UpdateDocumentTypeCommand {

    /**
     * ES主键id
     */
    @ApiModelProperty(value = "ES主键id")
    private String id;

    /**
     * 文档类型
     */
    @ApiModelProperty(value = "文档类型")
    private Integer type;
}
