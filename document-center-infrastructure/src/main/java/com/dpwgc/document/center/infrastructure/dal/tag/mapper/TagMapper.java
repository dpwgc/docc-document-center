package com.dpwgc.document.center.infrastructure.dal.tag.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dpwgc.document.center.infrastructure.dal.tag.entity.TagPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TagMapper extends BaseMapper<TagPO> {
}
