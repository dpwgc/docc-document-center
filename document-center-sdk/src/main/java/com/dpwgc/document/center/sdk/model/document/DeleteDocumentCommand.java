package com.dpwgc.document.center.sdk.model.document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "删除文档-接口参数")
@Getter
@Setter
public class DeleteDocumentCommand {

    /**
     * ES主键id
     */
    @ApiModelProperty(value = "ES主键id")
    private String id;
}
