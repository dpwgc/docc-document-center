package com.dpwgc.document.center.sdk.model.document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "更新文档收藏数-接口参数")
@Getter
@Setter
public class UpdateDocumentLoveCommand {

    /**
     * ES主键id
     */
    @ApiModelProperty(value = "ES主键id")
    private String id;

    /**
     * 文档收藏数（可按此字段对文档进行排序）
     */
    @ApiModelProperty(value = "文档收藏数（可按此字段对文档进行排序）")
    private Long love;
}
