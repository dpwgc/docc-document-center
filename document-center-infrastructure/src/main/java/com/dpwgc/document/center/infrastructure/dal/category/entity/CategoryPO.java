package com.dpwgc.document.center.infrastructure.dal.category.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("category")
public class CategoryPO {

    /**
     * 主键id
     */
    private String id;

    /**
     * 所属应用id
     */
    private String appId;

    /**
     * 分类id
     */
    private String categoryId;

    /**
     * 父类id（如果是空字符串则表示这是顶级类别）
     */
    private String parentId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类详情
     */
    private String detail;

    /**
     * 分类推荐分值（可按此字段对分类进行排序）
     */
    private Long score;

    /**
     * 分类状态（0-删除，1-正常）
     */
    private Integer status;

    /**
     * 分类创建时间
     */
    private Long createTime;

    /**
     * 分类更新时间
     */
    private Long updateTime;
}
