package com.dpwgc.document.center.app.query.tag.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dpwgc.document.center.app.assembler.TagAssembler;
import com.dpwgc.document.center.app.query.tag.TagQueryService;
import com.dpwgc.document.center.infrastructure.dal.tag.entity.TagPO;
import com.dpwgc.document.center.infrastructure.dal.tag.mapper.TagMapper;
import com.dpwgc.document.center.infrastructure.util.StringUtil;
import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.base.Status;
import com.dpwgc.document.center.sdk.model.tag.TagDTO;
import com.dpwgc.document.center.sdk.model.tag.TagQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagQueryServiceImpl implements TagQueryService {

    @Resource
    TagMapper tagMapper;

    /**
     * 获取在指定时间区间内活跃的标签列表（按number文档数量降序排序）
     * @param tagQuery 检索条件
     * @return List<TagDTO>
     */
    @Override
    public PageBase<List<TagDTO>> queryTag(TagQuery tagQuery) {

        PageBase<List<TagDTO>> pageBase = new PageBase<>();

        Page<TagPO> page = new Page<>(tagQuery.getPageIndex(), tagQuery.getPageSize());

        QueryWrapper<TagPO> queryWrapper = new QueryWrapper<>();

        //要查询的字段
        queryWrapper.select("tag_name","number","create_time","update_time");

        //查询指定应用的标签
        if (StringUtil.notEmpty(tagQuery.getAppId())) {
            queryWrapper.eq("app_id",tagQuery.getAppId());
        }
        queryWrapper.eq("status", Status.NORMAL);

        //匹配时间区间
        if (tagQuery.getStartUpdateTime() != null) {
            queryWrapper.ge("update_time",tagQuery.getStartUpdateTime()); // >=
        }
        if (tagQuery.getEndUpdateTime() != null) {
            queryWrapper.lt("update_time",tagQuery.getEndUpdateTime());   // <
        }

        //排序
        if (tagQuery.getSortOrder().equals("desc")) {
            queryWrapper.orderByDesc(tagQuery.getSortField());
        } else {
            queryWrapper.orderByAsc(tagQuery.getSortField());
        }

        //分页
        Page<TagPO> tagPOS = tagMapper.selectPage(page,queryWrapper);
        List<TagDTO> tagDTOS = new ArrayList<>();
        for (TagPO tagPO : tagPOS.getRecords()) {
            tagDTOS.add(TagAssembler.INSTANCE.assembleTagDTO(tagPO));
        }

        pageBase.setList(tagDTOS);
        pageBase.setTotal(tagPOS.getTotal());

        return pageBase;
    }
}
