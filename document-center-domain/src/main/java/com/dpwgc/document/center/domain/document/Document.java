package com.dpwgc.document.center.domain.document;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

/**
 * 文档
 */
@Setter
@Getter
public class Document {

    /**
     * ES主键id
     */
    private String id;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 分类id
     */
    private String categoryId;

    /**
     * 作者id
     */
    private Set<String> authorId;

    /**
     * 文档id
     */
    private String documentId;

    /**
     * 文档标题
     */
    private String title;

    /**
     * 文档内容
     */
    private String content;

    /**
     * 文档标签
     */
    private Set<String> tag;

    /**
     * 文档备注
     */
    private String remarks;

    /**
     * 文档附加内容（自定义）
     */
    private String extra;

    /**
     * 文档总结摘要
     */
    private String summary;

    /**
     * 文档查看权限级别（0～99自定义，用户权限等级如果低于authLevel，则不能查看该文章）
     */
    private Integer authLevel;

    /**
     * 文档推荐分值（可按此字段对文档进行排序，用于热门文档推荐）
     */
    private Long score;

    /**
     * 文档收藏数（可按此字段对文档进行排序）
     */
    private Long loveNum;

    /**
     * 文档点赞数（可按此字段对文档进行排序）
     */
    private Long likeNum;

    /**
     * 文档阅读数（可按此字段对文档进行排序）
     */
    private Long readNum;

    /**
     * 文档分享数（可按此字段对文档进行排序）
     */
    private Long shareNum;

    /**
     * 文档评论总数
     */
    private Long commentNum;

    /**
     * 文档检索过滤条件（自定义，例：1-仅自己可见、2-所有人可见）
     */
    private Set<Integer> filter;

    /**
     * 文档属性（自定义，例：1-转载文章、2-原创文章）
     */
    private Set<Integer> attr;

    /**
     * 文档类型（自定义，例：0-普通文档，1-置顶文档）
     */
    private Integer type;

    /**
     * 文档状态（0-删除，1-正常）
     */
    private Integer status;

    /**
     * 文档创建时间
     */
    private Long createTime;

    /**
     * 文档更新时间
     */
    private Long updateTime;

    protected Document create(String appId, String categoryId, Set<String> authorId, String documentId, String title, String content, Set<String> tag, String summary, String extra, String remarks, Integer authLevel, Long score, Set<Integer> filter, Set<Integer> attr, Integer type) {

        this.status = 1;

        this.appId = appId;
        this.categoryId = categoryId;
        this.authorId = authorId;
        this.documentId = documentId;
        this.title = title;
        this.content = content;
        this.summary = summary;
        this.extra = extra;
        this.remarks = remarks;
        this.tag = tag;
        this.authLevel = authLevel;

        this.score = score;

        this.loveNum = 0L;
        this.likeNum = 0L;
        this.readNum = 0L;
        this.shareNum = 0L;
        this.commentNum = 0L;

        this.filter = filter;
        this.attr = attr;
        this.type = type;

        this.createTime = System.currentTimeMillis();
        this.updateTime = System.currentTimeMillis();

        return this;
    }
}
