package com.dpwgc.document.center.app.query.tag;

import com.dpwgc.document.center.sdk.model.tag.TagDTO;

import java.util.List;

public interface TagQueryService {

    /**
     * 获取在指定时间区间内活跃的标签列表（按number文档数量降序排序）
     * @param appId 应用id
     * @param startUpdateTime 标签更新时间区间的起始位置
     * @param endUpdateTime 标签更新时间区间的结束位置
     * @param pageSize 最多返回几个标签
     * @return List<TagDTO>
     */
    List<TagDTO> listTagsByNumberDesc(String appId, Long startUpdateTime, Long endUpdateTime, Integer pageSize);
}
