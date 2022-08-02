package com.dpwgc.document.center.app.query.tag.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dpwgc.document.center.app.assembler.TagAssembler;
import com.dpwgc.document.center.app.query.tag.TagQueryService;
import com.dpwgc.document.center.infrastructure.dal.tag.entity.TagPO;
import com.dpwgc.document.center.infrastructure.dal.tag.mapper.TagMapper;
import com.dpwgc.document.center.sdk.base.Status;
import com.dpwgc.document.center.sdk.model.tag.TagDTO;
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
     * @param appId 应用id
     * @param startUpdateTime 标签更新时间区间的起始位置
     * @param endUpdateTime 标签更新时间区间的结束位置
     * @param pageSize 最多返回几个标签
     * @return List<TagDTO>
     */
    @Override
    public List<TagDTO> listTagsByNumberDesc(String appId, Long startUpdateTime, Long endUpdateTime, Integer pageSize) {

        Page<TagPO> page = new Page<>(0, pageSize);

        QueryWrapper<TagPO> queryWrapper = new QueryWrapper<>();

        //要查询的字段
        queryWrapper.select("tag_name","number","create_time","update_time");

        //查询指定应用的标签
        queryWrapper.eq("app_id",appId);
        queryWrapper.eq("status", Status.NORMAL);

        //匹配时间区间
        queryWrapper.ge("update_time",startUpdateTime); // >=
        queryWrapper.lt("update_time",endUpdateTime);   // <

        //排序
        queryWrapper.orderByDesc("number");

        //分页
        Page<TagPO> tagPOS = tagMapper.selectPage(page,queryWrapper);
        List<TagDTO> tagDTOS = new ArrayList<>();
        for (TagPO tagPO : tagPOS.getRecords()) {
            tagDTOS.add(TagAssembler.INSTANCE.assembleTagDTO(tagPO));
        }
        return tagDTOS;
    }
}
