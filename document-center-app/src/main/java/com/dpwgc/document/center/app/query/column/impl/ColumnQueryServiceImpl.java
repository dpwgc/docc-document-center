package com.dpwgc.document.center.app.query.column.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dpwgc.document.center.app.assembler.ColumnAssembler;
import com.dpwgc.document.center.app.query.column.ColumnQueryService;
import com.dpwgc.document.center.infrastructure.dal.column.entity.ColumnPO;
import com.dpwgc.document.center.infrastructure.dal.column.mapper.ColumnMapper;
import com.dpwgc.document.center.infrastructure.util.StringUtil;
import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.base.Status;
import com.dpwgc.document.center.sdk.model.column.ColumnDTO;
import com.dpwgc.document.center.sdk.model.column.ColumnQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ColumnQueryServiceImpl implements ColumnQueryService {

    @Resource
    ColumnMapper columnMapper;

    public PageBase<List<ColumnDTO>> queryColumn(ColumnQuery columnQuery) {
        PageBase<List<ColumnDTO>> pageBase = new PageBase<>();

        Page<ColumnPO> page = new Page<>(columnQuery.getPageIndex(), columnQuery.getPageSize());

        QueryWrapper<ColumnPO> queryWrapper = new QueryWrapper<>();

        if (StringUtil.notEmpty(columnQuery.getAppId())) {
            queryWrapper.eq("app_id",columnQuery.getAppId());
        }
        queryWrapper.eq("status", Status.NORMAL);

        if (StringUtil.notEmpty(columnQuery.getAuthorId())) {
            queryWrapper.eq("author_id",columnQuery.getAuthorId());
        }
        if (StringUtil.notEmpty(columnQuery.getKeyword())) {
            queryWrapper.like("column_name",columnQuery.getKeyword());
            queryWrapper.like("detail",columnQuery.getKeyword());
        }

        if (columnQuery.getAttr() != null) {
            queryWrapper.eq("attr",columnQuery.getAttr());
        }

        if (columnQuery.getType() != null) {
            queryWrapper.eq("type",columnQuery.getType());
        }

        if (columnQuery.getSortOrder().equals("desc")) {
            queryWrapper.orderByDesc(columnQuery.getSortField());
        } else {
            queryWrapper.orderByAsc(columnQuery.getSortField());
        }

        Page<ColumnPO> columnPOS = columnMapper.selectPage(page, queryWrapper);
        List<ColumnDTO> columnDTOS = new ArrayList<>();
        for (ColumnPO columnPO : columnPOS.getRecords()) {
            ColumnDTO columnDTO = ColumnAssembler.INSTANCE.assembleColumnDTO(columnPO);
            columnDTOS.add(columnDTO);
        }

        pageBase.setTotal(columnPOS.getTotal());
        pageBase.setList(columnDTOS);

        return pageBase;
    }
}
