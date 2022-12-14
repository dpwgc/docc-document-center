package com.dpwgc.document.center.app.query.tag;

import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.model.tag.TagDTO;
import com.dpwgc.document.center.sdk.model.tag.TagQuery;

import java.util.List;

public interface TagQueryService {

    /**
     * 获取在指定时间区间内活跃的标签列表
     * @param tagQuery 检索条件
     * @return List<TagDTO>
     */
    PageBase<List<TagDTO>> queryTag(TagQuery tagQuery);
}
