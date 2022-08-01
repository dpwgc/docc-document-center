package com.dpwgc.document.center.infrastructure.dal.tag.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("tag")
public class TagPO {

    /**
     * 主键id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private String id;

    /**
     * 所属应用id
     */
    private String appId;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 带有该标签的文档数量
     */
    private Long number;

    /**
     * 标签状态（0-隐藏，1-正常）
     */
    private Integer status;

    /**
     * 标签创建时间
     */
    private Long createTime;

    /**
     * 标签更新时间
     */
    private Long updateTime;
}
