package com.dpwgc.document.center.infrastructure.dal.category.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("category")
public class CategoryPO {

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
     * 分类附加内容（自定义）
     */
    private String extra;

    /**
     * 分类推荐分值（自由分配，返回分类列表时将按此字段对分类进行排序）
     */
    private Long score;

    /**
     * 分类属性（自定义，例：1-热门分类、0-普通分类）
     */
    private Integer attr;

    /**
     * 分类类型（自定义，例：1-官方分类，0-普通分类）
     */
    private Integer type;

    /**
     * 分类状态（0-删除，1-正常）
     */
    private Integer status;

    /**
     * 版本号（乐观锁字段，递增）
     */
    @Version
    private Long version;

    /**
     * 分类创建时间
     */
    private Long createTime;

    /**
     * 分类更新时间
     */
    private Long updateTime;
}
