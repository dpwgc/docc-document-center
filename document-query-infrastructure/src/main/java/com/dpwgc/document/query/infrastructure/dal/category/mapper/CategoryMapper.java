package com.dpwgc.document.query.infrastructure.dal.category.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dpwgc.document.query.infrastructure.dal.category.entity.CategoryPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CategoryMapper extends BaseMapper<CategoryPO> {
}
