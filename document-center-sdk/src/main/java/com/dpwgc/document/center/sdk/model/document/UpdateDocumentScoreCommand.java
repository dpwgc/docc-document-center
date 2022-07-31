package com.dpwgc.document.center.sdk.model.document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "更新文档推荐评分-接口参数")
@Getter
@Setter
public class UpdateDocumentScoreCommand {

    /**
     * ES主键id
     */
    @ApiModelProperty(value = "ES主键id")
    private String id;

    /**
     * 文档推荐分值（自定义，可按此字段对文档进行排序，用于热门文档推荐）
     */
    @ApiModelProperty(value = "文档推荐分值（自定义，可按此字段对文档进行排序，用于热门文档推荐）")
    private Long score;
}
