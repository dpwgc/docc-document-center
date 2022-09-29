package com.dpwgc.document.center.infrastructure.dal.column.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dpwgc.document.center.infrastructure.dal.column.entity.ColumnPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ColumnMapper extends BaseMapper<ColumnPO> {
}
